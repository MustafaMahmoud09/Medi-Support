package com.example.room.presentation.uiElement.screens.room

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.room.presentation.uiElement.components.items.VideoCallToolsSection
import com.example.room.presentation.uiElement.data.VideoToolData
import com.example.sharedui.uiElement.components.composable.FloatingActionButtonView
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import com.example.sharedui.R

@Composable
internal fun OnlineRoomScreen() {

    OnlineRoomContent(
        soundData = VideoToolData(
            icon = painterResource(
                id = R.drawable.sound_close_icon
            ),
            onClick = {},
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
            icon = painterResource(
                id = R.drawable.camera_opened_icon
            ),
            onClick = {},
            position = 2
        ),
        rotateData = VideoToolData(
            icon = painterResource(
                id = R.drawable.rotate_icon
            ),
            onClick = {},
            position = 3
        )
    )
}//end ChatScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun OnlineRoomContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    soundData: VideoToolData,
    chatData: VideoToolData,
    cameraData: VideoToolData,
    rotateData: VideoToolData
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
                    onClick = { /*TODO*/ },
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
            ){
                //create ids for screen components here


            }//end ConstraintLayout

        }//end Scaffold

    }//end BaseScreen

}//end ChatContent