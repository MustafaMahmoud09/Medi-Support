@file:OptIn(
    ExperimentalLayoutApi::class, ExperimentalComposeUiApi::class,
    ExperimentalFoundationApi::class
)

package com.example.presentation.uiElement.screens.chat

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.chat.domain.model.MessageModel
import com.example.presentation.uiElement.components.items.ChatDocumentSection
import com.example.presentation.uiElement.components.items.ChatToolsSection
import com.example.presentation.uiElement.components.items.GuestUserMessageSection
import com.example.presentation.uiElement.components.items.OwnerUserMessageSection
import com.example.presentation.uiElement.components.items.UserActiveSection
import com.example.presentation.uiState.state.ChatUiState
import com.example.presentation.uiState.viewModel.ChatViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.LoadImageViewII
import com.example.sharedui.uiElement.components.composable.ServerLoadImageView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.modifier.appDefaultContainer
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction3

@Composable
internal fun ChatScreen(
    popChatDestination: () -> Unit,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

    //get keyboard visible
    val isKeyboardVisible = WindowInsets.isImeVisible

    val keyboardController = LocalSoftwareKeyboardController.current


    //create files launcher here
    val fileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = viewModel::onCollectFileResult
    )

    //create camera launcher here
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success: Boolean ->
        if (success) {
            viewModel.onImageSelected()
        }
    }//end rememberLauncherForActivityResult

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            cameraLauncher.launch(viewModel.createImageUri())
        }
    }//end rememberLauncherForActivityResult

    ChatContent(
        uiState = state.value,
        chatState = rememberLazyListState(),
        messageStatus = state.value.totalMessagesStatus?.collectAsLazyPagingItems(),
        setFirstMessageVisibleInfo = viewModel::setFirstMessageVisibleInfo,
        onMessageChanged = viewModel::onMessageChanged,
        onSendMessage = viewModel::onSendMessage,
        onOpenDownloadFile = viewModel::onOpenDownloadFile,
        onDownloadFileCanceled = viewModel::onDownloadFileCanceled,
        onDownloadFile = viewModel::onDownloadFile,
        onClickOnBackButton = {

            //if keyboard is visible close keyboard
            if (isKeyboardVisible) {
                keyboardController!!.hide()
            }//end if

            //else pop destination from back stack
            else {
                popChatDestination()
            }//end else

        },
        keyboardIsVisible = isKeyboardVisible,
        onCancelFileUri = {
            if (isKeyboardVisible) {
                keyboardController!!.hide()
            } else {
                viewModel.onCancelFileUri()
            }
        },
        onClickOnOpenFileButton = {
            fileLauncher.launch(
                arrayOf(
                    "image/png",
                    "image/jpeg",
                    "image/jpg",
                    "image/gif",
                    "application/zip",
                    "application/x-rar-compressed",
                    "text/plain"
                )
            )
        },
        onClickOnOpenCameraButton = {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    )

    BackHandler {
        //if keyboard is visible close keyboard
        if (isKeyboardVisible) {
            keyboardController!!.hide()
        }//end if

        //else pop destination from back stack
        else {
            if (state.value.file.fileSelected) {
                viewModel.onCancelFileUri()
            } else if (state.value.downloadStatus.fileSelected) {
                viewModel.onDownloadFileCanceled()
            } else {
                popChatDestination()
            }
        }//end else

    }//end BackHandler
}//end ChatScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun ChatContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    keyboardIsVisible: Boolean,
    onClickOnBackButton: () -> Unit,
    onClickOnOpenFileButton: () -> Unit,
    onClickOnOpenCameraButton: () -> Unit,
    uiState: ChatUiState,
    messageStatus: LazyPagingItems<MessageModel>?,
    chatState: LazyListState,
    setFirstMessageVisibleInfo: KFunction2<Int, Int, Unit>,
    onMessageChanged: KFunction1<String, Unit>,
    onSendMessage: KFunction0<Unit>,
    onCancelFileUri: () -> Unit,
    onOpenDownloadFile: KFunction3<String, String, String, Unit>,
    onDownloadFileCanceled: KFunction0<Unit>,
    onDownloadFile: KFunction0<Unit>,
) {

    var chatInfoState by remember {
        mutableStateOf(false)
    }

    if (
        !uiState.file.fileSelected &&
        !uiState.downloadStatus.fileSelected
    ) {

        //create base screen to define navigation bar and status bar color
        BaseScreen(
            navigationColor = theme.background,
            statusColor = theme.background
        ) {

            Scaffold(
                topBar = {

                    TopAppBar(
                        modifier = Modifier
                            .fillMaxWidth(),
                        backgroundColor = theme.background,
                        contentPadding = PaddingValues(
                            bottom = dimen.dimen_3.dp
                        )
                    ) {

                        //create top bar content here
                        ChatTopBarContent(
                            dimen = dimen,
                            theme = theme,
                            uiState = uiState,
                            onClickOnBackButton = onClickOnBackButton
                        )

                    }//end TopAppBar

                },//end topBar
                modifier = Modifier
                    .appDefaultContainer(
                        color = theme.background
                    )
            ) {

                //padding from top screen = padding profile photo from top
                // + height profile photo
                // + padding profile photo from bottom
                // == 21 + 44 + 24
                //create container here
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = theme.background
                        )
                        .padding(
                            top = (
                                    dimen.dimen_2_5 +
                                            dimen.dimen_0_125 +
                                            dimen.dimen_4_5 +
                                            dimen.dimen_3
                                    ).dp
                        )
                ) {
                    //create ids for screen components here
                    val (chatToolsId, messagesContainerId) = createRefs()

                    //create chat tools section here
                    ChatToolsSection(
                        dimen = dimen,
                        theme = theme,
                        editTextValue = uiState.message,
                        onEditTextChanged = onMessageChanged,
                        hint = stringResource(
                            id = R.string.write_your_message
                        ),
                        onClickOnFileButton = onClickOnOpenFileButton,
                        onClickOnStickerButton = {},
                        onClickOnCameraButton = onClickOnOpenCameraButton,
                        onClickOnSoundButton = {},
                        onClickOnSendButton = onSendMessage,
                        keyboardIsVisible = keyboardIsVisible,
                        modifier = Modifier
                            .constrainAs(chatToolsId) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                                width = Dimension.fillToConstraints
                            }
                    )

//                if (messageStatus?.loadState?.refresh is LoadState.NotLoading) {

//                if (uiState.messageStatus.isNotEmpty()) {

                    //create column contain on all chat messages
                    LazyColumn(
                        state = chatState,
                        modifier = Modifier
                            .constrainAs(messagesContainerId) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(chatToolsId.top)
                                height = Dimension.fillToConstraints
                                width = Dimension.fillToConstraints
                            },
                        contentPadding = PaddingValues(
                            horizontal = dimen.dimen_2.dp,
                            vertical = dimen.dimen_1.dp
                        ),
                        verticalArrangement = Arrangement.Bottom
                    ) {

                        items(
                            count = uiState.messageStatus.size,
                            key = { uiState.messageStatus[it].messageId }
                        ) { count ->

                            if (uiState.messageStatus[count].fromId.toInt() != uiState.doctorId) {

                                OwnerUserMessageSection(
                                    dimen = dimen,
                                    theme = theme,
                                    messageModel = uiState.messageStatus[count],
                                    onOpenDownloadFile = onOpenDownloadFile,
                                    messageLoading = uiState.messageStatus[count].localId.isNotEmpty(),
                                    isEndMessage = uiState.messageStatus.size == 1 ||
                                            uiState.messageStatus.size - 1 == count ||
                                            uiState.messageStatus[count].fromId != uiState.messageStatus[count + 1].fromId,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .animateItemPlacement()
                                )

                            }//end if
                            else {

                                GuestUserMessageSection(
                                    dimen = dimen,
                                    theme = theme,
                                    doctor = uiState.onlineDoctorDetailsStatus.data,
                                    messageModel = uiState.messageStatus[count],
                                    onOpenDownloadFile = onOpenDownloadFile,
                                    isEndMessage = uiState.messageStatus.size == 1 ||
                                            uiState.messageStatus.size - 1 == count ||
                                            uiState.messageStatus[count].fromId != uiState.messageStatus[count + 1].fromId,
                                    isStartMessage = count == 0 ||
                                            uiState.messageStatus[count].fromId != uiState.messageStatus[count - 1].fromId,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .animateItemPlacement()
                                )

                            }//end else

                        }//end items

                    }//end LazyColumn

                }//end ConstraintLayout

            }//end BaseScreen


        }//end Scaffold

    }//end if
    else {

        if (uiState.file.fileSelected) {

            //create base screen to define navigation bar and status bar color
            BaseScreen(
                navigationColor = theme.black,
                statusColor = theme.black,
                useDarkIcons = false
            ) {

                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                        .background(
                            color = theme.black
                        )
                ) {
                    //create ids for components here
                    val (file, chatDocumentId, backButtonId) = createRefs()

                    val guideFromTop25P = createGuidelineFromTop(0.25f)
                    val guideFromBottom25P = createGuidelineFromBottom(0.25f)

                    if (
                        uiState.file.type == "png" ||
                        uiState.file.type == "jpeg" ||
                        uiState.file.type == "jpg" ||
                        uiState.file.type == "gif" ||
                        uiState.file.type.isEmpty()
                    ) {

                        LoadImageViewII(
                            imageUri = uiState.file.fileUri,
                            modifier = Modifier
                                .constrainAs(file) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(guideFromTop25P)
                                    bottom.linkTo(guideFromBottom25P)
                                    width = Dimension.fillToConstraints
                                    height = Dimension.fillToConstraints
                                }//end file
                        )

                    }//end if

                    else {

                        ConstraintLayout(
                            modifier = Modifier
                                .constrainAs(file) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(guideFromTop25P)
                                    bottom.linkTo(guideFromBottom25P)
                                    width = Dimension.fillToConstraints
                                    height = Dimension.fillToConstraints
                                }
                                .background(
                                    color = theme.black333333
                                )
                        ) {
                            //create ids for components here
                            val (nameId, typeId, sizeId) = createRefs()

                            TextBoldView(
                                theme = theme,
                                dimen = dimen,
                                text = uiState.file.name,
                                size = dimen.dimen_2_5,
                                color = theme.background,
                                modifier = Modifier
                                    .constrainAs(nameId) {
                                        start.linkTo(
                                            parent.start,
                                            dimen.dimen_2.dp
                                        )
                                        end.linkTo(
                                            parent.end,
                                            dimen.dimen_2.dp
                                        )
                                        top.linkTo(parent.top)
                                        bottom.linkTo(parent.bottom)
                                        width = Dimension.preferredWrapContent
                                    }
                            )

                        }//end ConstraintLayout

                    }//end else

                    //create chat tools section here
                    ChatDocumentSection(
                        dimen = dimen,
                        theme = theme,
                        editTextValue = uiState.message,
                        onEditTextChanged = onMessageChanged,
                        hint = stringResource(
                            id = R.string.write_your_message
                        ),
                        onClickOnStickerButton = {},
                        onClickOnSendButton = onSendMessage,
                        modifier = Modifier
                            .constrainAs(chatDocumentId) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                                width = Dimension.fillToConstraints
                            }
                            .background(
                                color = theme.black
                            )
                    )


                    //create back button here
                    IconButtonView(
                        dimen = dimen,
                        theme = theme,
                        tint = theme.background,
                        onClick = onCancelFileUri,
                        modifier = Modifier
                            .constrainAs(backButtonId) {
                                start.linkTo(
                                    parent.start,
                                    dimen.dimen_2.dp
                                )
                                top.linkTo(
                                    parent.top,
                                    dimen.dimen_2.dp
                                )
                            }
                    )

                }//end ConstraintLayout

            }//end BaseScreen

        }//end if

        else if (uiState.downloadStatus.fileSelected) {


            //create base screen to define navigation bar and status bar color
            BaseScreen(
                navigationColor = theme.black,
                statusColor = theme.black,
                useDarkIcons = false
            ) {

                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                        .background(
                            color = theme.black
                        )
                ) {
                    //create ids for components here
                    val (file, chatDocumentId, backButtonId, downloadButtonId) = createRefs()

                    val guideFromTop25P = createGuidelineFromTop(0.25f)
                    val guideFromBottom25P = createGuidelineFromBottom(0.25f)

                    if (
                        uiState.downloadStatus.type == "png" ||
                        uiState.downloadStatus.type == "jpeg" ||
                        uiState.downloadStatus.type == "jpg" ||
                        uiState.downloadStatus.type == "gif"
                    ) {

                        ServerLoadImageView(
                            theme = theme,
                            dimen = dimen,
                            imageUrl = uiState.downloadStatus.url,
                            progressBarTrackColor = theme.black,
                            progressBarColor = theme.background,
                            modifier = Modifier
                                .constrainAs(file) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(guideFromTop25P)
                                    bottom.linkTo(guideFromBottom25P)
                                    width = Dimension.fillToConstraints
                                    height = Dimension.fillToConstraints
                                }//end file
                        )

                    }//end if

                    else {

                        ConstraintLayout(
                            modifier = Modifier
                                .constrainAs(file) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(guideFromTop25P)
                                    bottom.linkTo(guideFromBottom25P)
                                    width = Dimension.fillToConstraints
                                    height = Dimension.fillToConstraints
                                }
                                .background(
                                    color = theme.black333333
                                )
                        ) {
                            //create ids for components here
                            val (nameId, typeId, sizeId) = createRefs()

                            TextBoldView(
                                theme = theme,
                                dimen = dimen,
                                text = "${uiState.downloadStatus.localName}.${uiState.downloadStatus.type}",
                                size = dimen.dimen_2_5,
                                color = theme.background,
                                modifier = Modifier
                                    .constrainAs(nameId) {
                                        start.linkTo(
                                            parent.start,
                                            dimen.dimen_2.dp
                                        )
                                        end.linkTo(
                                            parent.end,
                                            dimen.dimen_2.dp
                                        )
                                        top.linkTo(parent.top)
                                        bottom.linkTo(parent.bottom)
                                        width = Dimension.preferredWrapContent
                                    }
                            )

                        }//end ConstraintLayout

                    }//end else

                    //create back button here
                    IconButtonView(
                        dimen = dimen,
                        theme = theme,
                        tint = theme.background,
                        onClick = onDownloadFileCanceled,
                        modifier = Modifier
                            .constrainAs(backButtonId) {
                                start.linkTo(
                                    parent.start,
                                    dimen.dimen_2.dp
                                )
                                top.linkTo(
                                    parent.top,
                                    dimen.dimen_2.dp
                                )
                            }
                    )


                    //create download button here
                    IconButtonView(
                        dimen = dimen,
                        theme = theme,
                        tint = theme.background,
                        onClick = onDownloadFile,
                        icon = painterResource(
                            id = R.drawable.icons8_download_64
                        ),
                        modifier = Modifier
                            .constrainAs(downloadButtonId) {
                                end.linkTo(
                                    parent.end,
                                    dimen.dimen_2.dp
                                )
                                top.linkTo(
                                    parent.top,
                                    dimen.dimen_2.dp
                                )
                            }
                    )

                }//end ConstraintLayout

            }//end BaseScreen

        }//end if

    }//end else

    LaunchedEffect(
        key1 = chatInfoState
    ) {

        coroutineScope {

            if (uiState.messageStatus.isNotEmpty()) {
                setFirstMessageVisibleInfo(
                    chatState.firstVisibleItemIndex,
                    chatState.firstVisibleItemScrollOffset
                )
            }//end if

            delay(250)

            chatInfoState = !chatInfoState
        }//end launch

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = uiState.scrollToFirstMessage
    ) {

        if (
            !uiState.firstLoadState &&
            uiState.messageStatus.isNotEmpty()
        ) {
            chatState.scrollToItem(uiState.messageStatus.size - 1)
        }//end if

    }//end LaunchedEffect

}//end ChatContent

@Composable
private fun ChatTopBarContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickOnBackButton: () -> Unit,
    uiState: ChatUiState
) {

    //create container here
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        //create ids for screen components here
        val (backButtonId, userActiveId) = createRefs()

        //create other user in top screen here
        UserActiveSection(
            dimen = dimen,
            theme = theme,
            doctor = uiState.onlineDoctorDetailsStatus.data,
            modifier = Modifier
                .constrainAs(userActiveId) {
                    start.linkTo(
                        parent.start,
                        (dimen.dimen_3_75 + dimen.dimen_3_5).dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_2.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create back button here
        IconButtonView(
            dimen = dimen,
            theme = theme,
            onClick = onClickOnBackButton,
            modifier = Modifier
                .constrainAs(backButtonId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(userActiveId.top)
                    bottom.linkTo(userActiveId.bottom)
                }
        )

    }//end ConstraintLayout

}//end ChatTopBarContent