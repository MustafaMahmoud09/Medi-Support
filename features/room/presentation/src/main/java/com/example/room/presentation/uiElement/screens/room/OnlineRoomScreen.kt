@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.room.presentation.uiElement.screens.room

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.room.presentation.uiElement.components.items.VideoCallToolsSection
import com.example.room.presentation.uiElement.data.VideoToolData
import com.example.room.presentation.uiState.state.OnlineRoomUiState
import com.example.room.presentation.uiState.viewModel.OnlineRoomViewModel
import com.example.sharedui.uiElement.components.composable.FloatingActionButtonView
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.twilio.video.VideoView

@Composable
internal fun OnlineRoomScreen(
    viewModel: OnlineRoomViewModel = hiltViewModel(),
    popOnlineRoomGraph: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    //make remember permission for camera and audio
    val videoCallPermission = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.RECORD_AUDIO
        )
    )

    OnlineRoomContent(
        soundData = VideoToolData(
            icon = if (state.value.localAudioEnable) {
                painterResource(
                    id = R.drawable.sound_openned_icon
                )
            } else {
                painterResource(
                    id = R.drawable.sound_close_icon
                )
            },
            onClick = viewModel::onChangeLocalAudioEnableStatus,
            position = 0
        ),
        chatData = VideoToolData(
            icon = painterResource(
                id = R.drawable.chat_icon
            ),
            onClick = {},
            position = 1
        ),
        cameraData = VideoToolData(
            icon = if (state.value.localVideoEnable) {
                painterResource(
                    id = R.drawable.camera_opened_icon
                )
            } else {
                painterResource(
                    id = R.drawable.camera_closed_icon
                )
            },
            onClick = viewModel::onChangeLocalVideoEnableStatus,
            position = 2
        ),
        rotateData = VideoToolData(
            icon = painterResource(
                id = R.drawable.rotate_icon
            ),
            onClick = viewModel::onRotateCamera,
            position = 3
        ),
        uiState = state.value,
        onClickOnCloseVideoCallButton = {
            viewModel.onCloseVideoCall()
            popOnlineRoomGraph()
        }//end onClickOnCloseVideoCallButton
    )

    //make permission request for camera and audio
    LaunchedEffect(
        key1 = videoCallPermission.allPermissionsGranted
    ) {

        if (!videoCallPermission.allPermissionsGranted) {
            videoCallPermission.launchMultiplePermissionRequest()
        }//end if
        else {
            viewModel.onInitializeLocalVideoComponent()
        }//end else

    }//end LaunchedEffect

}//end ChatScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun OnlineRoomContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    soundData: VideoToolData,
    chatData: VideoToolData,
    cameraData: VideoToolData,
    rotateData: VideoToolData,
    onClickOnCloseVideoCallButton: () -> Unit,
    uiState: OnlineRoomUiState
) {

    //create base screen for define status and navigation bar color
    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.transparent
    ) {

        //create scaffold to create bottom navigation contain on video call tools
        Scaffold(
            bottomBar = {

                //create bottom app bar here
                BottomAppBar(
                    cutoutShape = CircleShape,
                    backgroundColor = theme.background,
                    contentPadding = PaddingValues(
                        all = dimen.dimen_0.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(
                            dimen.dimen_10.dp
                        )
                ) {

                    //create video tools section here
                    VideoCallToolsSection(
                        dimen = dimen,
                        theme = theme,
                        soundData = soundData,
                        chatData = chatData,
                        cameraData = cameraData,
                        rotateData = rotateData,
                        modifier = Modifier
                            .fillMaxSize()
                    )

                }//end BottomAppBar

            },//end bottomBar scope
            floatingActionButton = {

                //create call button here
                FloatingActionButtonView(
                    dimen = dimen,
                    theme = theme,
                    icon = painterResource(
                        id = R.drawable.call_icon
                    ),
                    iconSize = dimen.dimen_3,
                    tint = theme.background,
                    floatingSize = dimen.dimen_7_5,
                    floatingColor = theme.redDark,
                    shadowColor = theme.redDark,
                    elevation = dimen.dimen_0_75,
                    onClick = onClickOnCloseVideoCallButton,
                )

            },//end floatingActionButton
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true,
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
        ) {

            //create container here
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = dimen.dimen_8_25.dp
                    )
            ) {
                //create ids for screen components here
                val (remoteVideoId, localVideoId, gtInfoSectionId, timeSectionId) = createRefs()

                //if remote video track not null then show remote video
                if (uiState.remoteVideoTrack != null) {
//remote
                    Box(
                        modifier = Modifier
                            .constrainAs(remoteVideoId) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                width = Dimension.fillToConstraints
                                height = Dimension.fillToConstraints
                            }.background(
                                brush = Brush.linearGradient(
                                    listOf(theme.green7FF0F7, theme.green27E8F4)
                                )
                            )
                    ) {

                        if (uiState.remoteVideoTrack.isEnabled) {
//remote
                            AndroidView(
                                factory = {
                                    val videoView = VideoView(it)
                                    videoView
                                },
                                modifier = Modifier
                                    .fillMaxSize()
                            ) { videoView ->
                                uiState.remoteVideoTrack.addSink(videoView)
                            }//end AndroidView
//remote
                        }//end if

                    }//end Box

                }//end if

                //if local video track not null then show local video
                if (uiState.localVideoTrack != null) {

                    //if remote video not null then show small local video
                    if (uiState.remoteVideoTrack != null) {
//!
                        var localVideoModifier = Modifier
                            .constrainAs(localVideoId) {
                                end.linkTo(
                                    parent.end,
                                    dimen.dimen_3.dp
                                )
                                bottom.linkTo(
                                    parent.bottom,
                                    dimen.dimen_4_5.dp
                                )
                            }
                            .clip(
                                shape = RoundedCornerShape(
                                    size = dimen.dimen_1_5.dp
                                )
                            )
                            .size(
                                width = dimen.dimen_12.dp,
                                height = dimen.dimen_16.dp
                            )

                        if (!uiState.localVideoEnable) {
                            localVideoModifier = localVideoModifier
                                .background(
                                    brush = Brush.linearGradient(
                                        listOf(theme.green7FF0F7, theme.green27E8F4)
                                    )
                                )
                                .padding(
                                    horizontal = dimen.dimen_1_75.dp
                                )
                        }//end if

                        Box(
                            modifier = localVideoModifier,
                            contentAlignment = Alignment.Center
                        ) {

                            if (uiState.localVideoEnable) {

                                AndroidView(
                                    factory = {
                                        val videoView = VideoView(it)
                                        videoView
                                    },
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) { videoView ->
                                    uiState.localVideoTrack.addSink(videoView)
                                }//end AndroidView

                            }//end if
                            else {
                                TextSemiBoldView(
                                    theme = theme,
                                    dimen = dimen,
                                    text = stringResource(R.string.you_are_not_the_camera_operator),
                                    size = dimen.dimen_1_5,
                                    fontColor = theme.black,
                                    textAlign = TextAlign.Center
                                )
                            }//end else

                        }//end Box

                    }//end if

                    //else show in all screen
                    else {

                        Box(
                            modifier = Modifier
                                .constrainAs(localVideoId) {
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    top.linkTo(parent.top)
                                    height = Dimension.fillToConstraints
                                    width = Dimension.fillToConstraints
                                }
                                .background(
                                    brush = Brush.linearGradient(
                                        listOf(theme.green7FF0F7, theme.green27E8F4)
                                    )
                                ),
                        ) {

                            if (uiState.localVideoEnable) {

                                AndroidView(
                                    factory = {
                                        val videoView = VideoView(it)
                                        videoView
                                    },
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) { videoView ->
                                    uiState.localVideoTrack.addSink(videoView)
                                }//end AndroidView

                            }//end if

                        }//end Box

                    }//end else

                }//end if

            }//end ConstraintLayout

        }//end Scaffold

    }//end BaseScreen

}//end ChatContent