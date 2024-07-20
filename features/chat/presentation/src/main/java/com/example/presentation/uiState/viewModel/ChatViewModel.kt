package com.example.presentation.uiState.viewModel

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.provider.OpenableColumns
import android.util.Log
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.chat.domain.model.AttachmentModel
import com.example.chat.domain.model.MessageModel
import com.example.chat.domain.usecase.declarations.IGetDoctorUseCase
import com.example.chat.domain.usecase.declarations.IGetPageMessageUseCase
import com.example.chat.domain.usecase.declarations.IGetAuthUserInfoUseCase
import com.example.chat.domain.usecase.declarations.ISeenChatMessagesUseCase
import com.example.chat.domain.usecase.declarations.ISendChatMessageUseCase
import com.example.libraries.core.remote.data.response.status.Status
import com.example.presentation.uiElement.screens.chat.ChatArgs
import com.example.presentation.uiState.BroadCastData.MessageEvent.MessageRoot
import com.example.presentation.uiState.state.ChatUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import com.google.gson.Gson
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.LinkedList
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ChatViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDoctorUseCase: IGetDoctorUseCase,
    private val getPageMessageUseCase: IGetPageMessageUseCase,
    private val getProfileInfoUseCase: IGetAuthUserInfoUseCase,
    private val seenChatMessagesUseCase: ISeenChatMessagesUseCase,
    private val sendChatMessageUseCase: ISendChatMessageUseCase,
    @ApplicationContext private val context: Context,
    @Named("cluster") private val cluster: String,
    @Named("pusher_key") private val pusherKey: String,
    @Named("domain") private val baseUrl: String
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(ChatUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    //get booking arguments here
    private val arguments: ChatArgs =
        ChatArgs(
            savedStateHandle
        )

    init {

        _state.update {
            it.copy(
                doctorId = arguments.chatId
            )
        }//end update
        onGetDoctorDetails()
        onGetTotalChatMessages()
        onObservePusherChanel()
        onSeenChatMessages()

    }//end init

    fun onMessageChanged(newValue: String) {

        _state.update {
            it.copy(
                message = newValue
            )
        }//end update

    }//end onMessageChanged


    fun onSendMessage() {

        viewModelScope.launch(Dispatchers.IO) {

            if (
                state.value.message.trim().isNotEmpty() ||
                state.value.file.fileSelected
            ) {
                val localMessageId = getRandomString(13) + state.value.localMessageId


                Log.d("TAG_LOCAL", localMessageId)

                _state.update {
                    it.copy(
                        localMessageId = state.value.localMessageId + 1
                    )
                }//end update
                val message = state.value.message.trim()
                val fileSelectedTemp = state.value.file.fileSelected
                val fileUri = state.value.file.fileUri
                val fileType = state.value.file.type
                Log.d("TAG_FILE", fileSelectedTemp.toString())
                Log.d("TAG_URI", fileUri.path.toString())
                onCancelFileUri()
                try {
                    sendChatMessageUseCase(
                        doctorId = state.value.doctorId,
                        message = message.ifEmpty { null },
                        temporaryMsgId = localMessageId,
                        uri = if (fileSelectedTemp) fileUri else null
                    ).collectLatest {

                        if (it is Status.Loading) {

                            val list = LinkedList<MessageModel>()
                            list.addAll(state.value.messageStatus)
                            list.add(
                                element = MessageModel(
                                    messageId = localMessageId,
                                    fromId = state.value.currentUserAuthId,
                                    toId = state.value.doctorId.toLong(),
                                    body = message,
                                    attachment = AttachmentModel(
                                        newName = "",
                                        ex = fileType,
                                        oldName = "tempFile"
                                    ),
                                    seen = true,
                                    time = "",
                                    localId = localMessageId
                                )
                            )

                            _state.update {
                                it.copy(
                                    messageStatus = list,
                                    loadingMessageCount = state.value.loadingMessageCount + 1
                                )
                            }//end update

                        }//end if

                    }
                }//end try
                catch (ex: Exception) {
                }//end catch
            }//end if

        }//end coroutine builder scope

    }//end onSendMessage


    private fun onSeenChatMessages() {

        viewModelScope.launch(Dispatchers.IO) {

            seenChatMessagesUseCase(
                doctorId = state.value.doctorId
            ).collectLatest {

            }//end collectLatest

        }//end coroutine builder scope

    }//end onSeenChatMessages


    fun setFirstMessageVisibleInfo(index: Int, offset: Int) {

        _state.value.firstItemVisibleIndex.update {
            index
        }//end

        _state.value.firstItemVisibleOffset.update {
            offset
        }//end

    }//end setFirstMessageVisibleInfo


    private fun onObservePusherChanel() {

        viewModelScope.launch(Dispatchers.IO) {

            getProfileInfoUseCase().collectLatest { list ->

                if (list.isNotEmpty()) {

                    val options = PusherOptions()
                    options.setCluster(cluster)

                    val pusher = Pusher(pusherKey, options)

                    pusher.connect()

                    _state.update {
                        it.copy(
                            currentUserAuthId = list[0].id
                        )
                    }

                    val channel = pusher.subscribe("chatify.${list[0].id}")
                    channel.bind("messaging") { event ->
                        val root = Gson().fromJson(event.data, MessageRoot::class.java)
                        Log.d("TAG_EVENT_DATA", event.data)
                        onCollectNewMessageFromServer(
                            root = root
                        )
                        onSeenChatMessages()
                    }//end bind

                }//end if

            }//end collectLatest

        }//end Launch

    }//end onObservePusherChanel


    //function for collect new message from server
    private fun onCollectNewMessageFromServer(root: MessageRoot) {

        if (
            state.value.doctorId.toLong() == root.fromId ||
            state.value.doctorId.toLong() == root.toId
//            !messageExist
        ) {

            var messageExistAsTempMessage = false

            Log.d("TAG_TO", root.toId.toString())
            Log.d("TAG_FROM", root.fromId.toString())
            Log.d("TAG_temp", root.tempID.toString())

            if (
                state.value.doctorId.toLong() == root.toId &&
                root.tempID != null
            ) {

                for (index in 0 until state.value.messageStatus.size) {

                    if (
                        root.tempID ==
                        state.value.messageStatus[state.value.messageStatus.size - index - 1].localId
                    ) {
                        _state.update {
                            it.copy(
                                loadingMessageCount = state.value.loadingMessageCount - 1
                            )
                        }//end update
                        messageExistAsTempMessage = true
                        break
                    }//end if

                }//end for

            }//end if

            if (!messageExistAsTempMessage) {

                val newMessage = MessageModel(
                    messageId = root.message?.id ?: "",
                    fromId = root.fromId ?: 0,
                    toId = root.toId ?: 0,
                    body = root.message?.message ?: "",
                    attachment = AttachmentModel(
                        newName = baseUrl + "attachments/" + (root.message?.attachment?.file ?: ""),
                        oldName = (root.message?.attachment?.title ?: "").substringBefore('.'),
                        ex = (root.message?.attachment?.file ?: "").subStringFromEndBefore('.')
                    ),
                    seen = root.message?.seen != null,
                    time = formatMessageTime(root.message?.createdAt ?: "")
                )

                val list = LinkedList<MessageModel>()
                if (state.value.messageStatus.isNotEmpty()) {
                    list.addAll(state.value.messageStatus)
                }
                list.add(newMessage)

                _state.update {
                    it.copy(
                        messageStatus = list
                    )
                }//end update

            }//end if
            else {

                val list = state.value.messageStatus.map { model ->
                    if (root.tempID == model.localId) {
                        MessageModel(
                            messageId = model.localId,
                            fromId = root.fromId ?: 0,
                            toId = root.toId ?: 0,
                            body = root.message?.message ?: "",
                            attachment = AttachmentModel(
                                newName = baseUrl + "attachments/" + (root.message?.attachment?.file
                                    ?: ""),
                                oldName = (root.message?.attachment?.title ?: "").substringBefore(
                                    '.'
                                ),
                                ex = (root.message?.attachment?.file ?: "").subStringFromEndBefore(
                                    '.'
                                )
                            ),
                            seen = root.message?.seen != null,
                            time = formatMessageTime(root.message?.createdAt ?: "")
                        )
                    }//end if
                    else {
                        model
                    }//end else
                }//end map

                _state.update {
                    it.copy(
                        messageStatus = list
                    )
                }//end update

            }//end else

            if (state.value.doctorId.toLong() == root.fromId) {
                _state.update {
                    it.copy(
                        scrollToFirstMessage = !state.value.scrollToFirstMessage
                    )
                }//end update
            }//end if

        }//end if

    }//end onCollectNewMessageFromServer


    //function for get chat doctor
    //function for get doctor details
    private fun onGetDoctorDetails() {

        //create coroutine builder for call suspend functions in it
        viewModelScope.launch(Dispatchers.IO) {

            try {

                getDoctorUseCase(
                    doctorId = state.value.doctorId
                ).collectLatest { status ->

                    when (status) {

                        is Status.Success -> {

                            if (status.toData()?.statusCode == 200) {

                                //update top offline doctors status to success
                                _state.update {
                                    it.copy(
                                        onlineDoctorDetailsStatus = state.value
                                            .onlineDoctorDetailsStatus.copy(
                                                loading = false,
                                                data = status.data.body
                                            )
                                    )
                                }//end update

                            }//end if

                            else if (status.toData()?.statusCode == 404) {

                                //update top offline doctors status to success
                                _state.update {
                                    it.copy(
                                        onlineDoctorDetailsStatus = state.value
                                            .onlineDoctorDetailsStatus.copy(
                                                doctorDeleted = true
                                            )
                                    )
                                }//end update

                            }//end else

                        }//end success case

                        is Status.Error -> {

                        }//end error case

                        is Status.Loading -> {

                            //update top offline doctors status to loading
                            _state.update {
                                it.copy(
                                    onlineDoctorDetailsStatus = state.value
                                        .onlineDoctorDetailsStatus.copy(
                                            loading = true
                                        )
                                )
                            }//end update

                        }//end error case

                    }//end when

                }//end collectLatest

            }//end try
            catch (ex: Exception) {
                ex.message?.let { Log.d("TAG", it) }
            }//end catch

        }//end coroutine builder scope

    }//end onGetDoctorDetails


    //function for get total offline doctors
    private fun onGetTotalChatMessages() {

        viewModelScope.launch(Dispatchers.IO) {

            _state.value.firstItemVisibleOffset.collectLatest {

                if (
                    (state.value.firstItemVisibleOffset.value == 0 &&
                            state.value.firstItemVisibleIndex.value == 0) ||
                    state.value.firstItemVisibleOffset.value == -1
                ) {

                    val page = ((state.value.messageStatus.size - state.value.loadingMessageCount)/ 10) + 1

                    val data = getPageMessageUseCase(
                        page = page,
                        perPage = 10,
                        doctorId = state.value.doctorId
                    )

                    val temp = LinkedList<MessageModel>()

                    Log.d("TAG_CHAT_DATA", data.body.toString())

                    if (page == (state.value.messageStatus.size / 10  - state.value.loadingMessageCount) + 1) {

                        var counter = 1

                        for (count in (state.value.messageStatus.size  - state.value.loadingMessageCount) % 10 until (data.body?.size
                            ?: 0)) {
                            temp.add(data.body!![data.body!!.size - counter])
                            counter += 1
                        }//end for

                        state.value.messageStatus.forEach {
                            temp.add(it)
                        }//end forEach

                        _state.update {
                            it.copy(
                                messageStatus = temp
                            )
                        }//end update

                        if (state.value.firstLoadState) {
                            _state.update {
                                it.copy(
                                    scrollToFirstMessage = !state.value.scrollToFirstMessage,
                                    firstLoadState = false
                                )
                            }//end update
                        }//end if

                    }//end if

                    Log.d("TAG_current", "${state.value.currentPage}")
                    Log.d("TAG_last", "${data.lastPageNumber}")

                }//end if

            }//end collectLatest

        }//end coroutine builder scope

    }//end onGetTotalOnlineDoctors


    fun onCollectFileResult(uri: Uri?) {

        if (uri != null) {

            _state.update {
                it.copy(
                    file = state.value.file.copy(
                        fileUri = uri,
                        fileSelected = true,
                        type = (getFileName(uri) ?: "").subStringFromEndBefore('.'),
                        name = getFileName(uri) ?: "",
                    ),
                    message = "",
                )
            }//end update

        }//end if

    }//end onCollectFileResult


    private fun getFileName(uri: Uri): String? {
        var fileName: String? = null
        if (uri.scheme == "content") {
            val cursor: Cursor? = context.contentResolver.query(uri, null, null, null, null)
            cursor?.use {
                if (it.moveToFirst()) {
                    val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (nameIndex != -1) {
                        fileName = it.getString(nameIndex)
                    }
                }
            }
        }
        if (fileName == null) {
            fileName = uri.path
            val cut = fileName?.lastIndexOf('/')
            if (cut != -1) {
                fileName = fileName?.substring(cut!! + 1)
            }
        }
        return fileName
    }//end getFileName



    fun onCancelFileUri() {

        File(
            context.filesDir, "chat_image_" + (state.value.countImageCaptured - 1) + ".jpg"
        ).deleteOnExit()
        _state.update {
            it.copy(
                file = state.value.file.copy(
                    fileUri = Uri.parse(""),
                    fileSelected = false,
                    type = "",
                    name = "",
                    size = ""
                ),
                message = ""
            )
        }//end update

    }//end onCollectFileResult


    fun createImageUri(): Uri {

        val imageFile = File(context.filesDir, "chat_image_${state.value.countImageCaptured}.jpg")

        _state.update {
            it.copy(
                countImageCaptured = state.value.countImageCaptured + 1
            )
        }//end update

        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            imageFile
        )

        File(
            context.filesDir, "chat_image_" + (state.value.countImageCaptured - 1) + ".jpg"
        ).deleteOnExit()

        _state.update {
            it.copy(
                file = state.value.file.copy(
                    fileUri = uri
                )
            )
        }//end update

        return uri
    }//end createImageUri


    fun onImageSelected() {

        _state.update {
            it.copy(
                file = state.value.file.copy(
                    fileSelected = true
                )
            )
        }//end update

    }//end onImageSelected


    fun onOpenDownloadFile(
        type: String,
        name: String,
        url: String
    ) {

        _state.update {
            it.copy(
                downloadStatus = state.value.downloadStatus.copy(
                    localName = name,
                    fileSelected = true,
                    type = type,
                    url = url
                ),
            )
        }//end update

    }//end onCollectFileResult


    fun onDownloadFileCanceled() {

        _state.update {
            it.copy(
                downloadStatus = state.value.downloadStatus.copy(
                    localName = "",
                    fileSelected = false,
                    type = "",
                    url = ""
                ),
            )
        }//end update

    }//end onDownloadFileCanceled


    //function for download file by download manager
    fun onDownloadFile() {

        //get instance for download manager
        if (state.value.downloadManager == null) {
            val downloadManager = context.getSystemService(DownloadManager::class.java)
            _state.update {
                it.copy(
                    downloadManager = downloadManager
                )
            }
        }//end if

        //make request on server for download image
        val request = DownloadManager.Request(state.value.downloadStatus.url.toUri())
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("${state.value.downloadStatus.localName}.${state.value.downloadStatus.type}")
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "${state.value.downloadStatus.localName}.${state.value.downloadStatus.type}"
            )

        //set download request in queue to executing
        state.value.downloadManager?.enqueue(request)

    }//end onDownloadFile


    private fun getRandomString(length: Int): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }//end getRandomString


}//end ChatViewModel