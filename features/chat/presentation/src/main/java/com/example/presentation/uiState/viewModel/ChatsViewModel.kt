package com.example.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.insertHeaderItem
import com.example.chat.domain.model.ChatModel
import com.example.chat.domain.usecase.declarations.IGetChannelAuthTokenUseCase
import com.example.chat.domain.usecase.declarations.IGetPageChatsUseCase
import com.example.chat.domain.usecase.declarations.IGetAuthUserInfoUseCase
import com.example.chat.pagination.TotalChatDataSource
import com.example.presentation.uiState.BroadCastData.SeenEvent.SeenRoot
import com.example.presentation.uiState.state.ChatsUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import com.google.gson.Gson
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val getChatsUseCase: IGetPageChatsUseCase,
    private val getChannelAuthTokenUseCase: IGetChannelAuthTokenUseCase,
    @Named("cluster") private val cluster: String,
    @Named("pusher_key") private val pusherKey: String,
    @Named("domain") private val baseUrl: String,
    private val getProfileInfoUseCase: IGetAuthUserInfoUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(ChatsUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {
        onGetTotalChats()
        onObservePusherChanel()
    }//end init

    //function for get total offline doctors
    private fun onGetTotalChats() {

        //get total offline doctors flow here
        val totalChatFlow = Pager(
            config = PagingConfig(
                pageSize = 10
            )
        ) {
            TotalChatDataSource(
                getPageChatUseCase = getChatsUseCase
            )
        }.flow
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)

        //change total offline doctor state here
        _state.update {
            it.copy(
                totalChatStatus = totalChatFlow
            )
        }//end update

    }//end onGetTotalOnlineDoctors


    private fun onObservePusherChanel() {

        viewModelScope.launch(Dispatchers.IO) {

            getProfileInfoUseCase().collectLatest { list ->

                if (list.isNotEmpty()) {

                    val options = PusherOptions()
                    options.setCluster(cluster)

                    val pusher = Pusher(pusherKey, options)

                    pusher.connect()

                    val channel = pusher.subscribe("seen.${list[0].id}")
                    channel.bind("messaging") { event ->
                        val root = Gson().fromJson(event.data, SeenRoot::class.java)
                        onCollectCountUnseenMessages(root)
                    }//end bind event

                }//end if

            }//end collectLatest

        }//end Launch

    }//end onObservePusherChanel


    private fun onCollectCountUnseenMessages(root: SeenRoot) {

        var chats = state.value.totalChatStatus?.map { data ->
            data.filter { chatModel ->
                chatModel.doctorId != root.chatId
            }//end filter
        }//end map

       chats = chats?.map { data ->
            data.insertHeaderItem(
                item = ChatModel(
                    doctorName = (root.lastMessage?.user?.firstName
                        ?: "") + " " + (root.lastMessage?.user?.lastName ?: ""),
                    doctorId = root.lastMessage?.user?.id ?: 0,
                    doctorImageUrl = baseUrl + (root.lastMessage?.user?.avatar ?: ""),
                    lastMessage = root.lastMessage?.lastMessage?.lastMessage ?: "",
                    activeStatus = root.lastMessage?.user?.activeStatus == 1,
                    unseenCount = if ((root.lastMessage?.unseenCounter
                            ?: 0) > 9
                    ) "+9" else "${root.lastMessage?.unseenCounter ?: 0}",
                    lastMessageTime = formatMessageTime(root.lastMessage?.lastMessage?.createdAt ?: "", "d-M-yyyy"),
                    doctorJobType = root.lastMessage?.user?.specialization ?: ""
                )
            )
        }//end map

        _state.update {
            it.copy(
                totalChatStatus = chats
            )
        }//end update

    }//end onCollectCountUnseenMessages


    fun onChatsBackupCreated() {

        _state.update {
            it.copy(
                chatsBackup = state.value.totalChatStatus
            )
        }

    }//end onChatsBackupCreated

}//end ChatsViewModel






