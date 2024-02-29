package com.example.room.presentation.uiElement.components.items

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.room.presentation.uiElement.data.VideoToolData
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun VideoCallToolsSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    soundData: VideoToolData,
    chatData: VideoToolData,
    cameraData: VideoToolData,
    rotateData: VideoToolData,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
    ) {
        //create ids for components here
        val (soundButtonId, chatButtonId, rotateButtonId, cameraButtonId) = createRefs()

        //create sound button here
        com.example.room.presentation.uiElement.components.composable.ToolButtonView(
            dimen = dimen,
            theme = theme,
            icon = soundData.icon,
            onClick = soundData.onClick,
            itemNumber = soundData.position,
            itemSelected = -1,
            modifier = Modifier
                .constrainAs(soundButtonId) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(
                        parent.start,
                        dimen.dimen_3.dp
                    )
                }
        )


        //create chat button here
        com.example.room.presentation.uiElement.components.composable.ToolButtonView(
            dimen = dimen,
            theme = theme,
            icon = chatData.icon,
            onClick = chatData.onClick,
            itemNumber = chatData.position,
            itemSelected = -1,
            modifier = Modifier
                .constrainAs(chatButtonId) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(
                        soundButtonId.end,
                        dimen.dimen_1_5.dp
                    )
                }
        )

        //create rotate button here
        com.example.room.presentation.uiElement.components.composable.ToolButtonView(
            dimen = dimen,
            theme = theme,
            icon = rotateData.icon,
            onClick = rotateData.onClick,
            itemNumber = rotateData.position,
            itemSelected = -1,
            modifier = Modifier
                .constrainAs(rotateButtonId) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(
                        parent.end,
                        dimen.dimen_3.dp
                    )
                }
        )

        //create camera button here
        com.example.room.presentation.uiElement.components.composable.ToolButtonView(
            dimen = dimen,
            theme = theme,
            icon = cameraData.icon,
            onClick = cameraData.onClick,
            itemNumber = cameraData.position,
            itemSelected = -1,
            modifier = Modifier
                .constrainAs(cameraButtonId) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(
                        rotateButtonId.start,
                        dimen.dimen_1_5.dp
                    )
                }
        )

    }//end ConstraintLayout

}//end VideoCallToolsSection