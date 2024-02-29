package com.example.presentation.uiElement.components.items

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.presentation.uiElement.components.composable.EndIconEditTextView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.FloatingActionButtonView
import com.example.sharedui.uiElement.components.composable.IconButtonView

@Composable
internal fun ChatToolsSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    borderColor: Color = theme.greenLightEEFAF8,
    borderHeight: Float = dimen.dimen_0_125,
    stickersIcon: Painter = painterResource(
        id = R.drawable.stickers_icon
    ),
    fileIcon: Painter = painterResource(
        id = R.drawable.file_icon
    ),
    fileButtonSize: Float = dimen.dimen_3,
    fileIconTint: Color = theme.black,
    soundIcon: Painter = painterResource(
        id = R.drawable.sound_icon
    ),
    soundButtonSize: Float = dimen.dimen_3,
    soundIconTint: Color = theme.black,
    cameraIcon: Painter = painterResource(
        id = R.drawable.camera_icon
    ),
    cameraButtonSize: Float = dimen.dimen_3,
    cameraIconTint: Color = theme.black,
    sendIcon: Painter = painterResource(
        id = R.drawable.send_icon
    ),
    sendIconSize: Float = dimen.dimen_2_25,
    sendIconTint: Color = theme.background,
    sendButtonSize: Float = dimen.dimen_5,
    sendButtonBackground: Color = theme.redDark,
    onClickOnStickerButton: () -> Unit,
    onClickOnFileButton: () -> Unit,
    onClickOnSoundButton: () -> Unit,
    onClickOnCameraButton: () -> Unit,
    onClickOnSendButton: () -> Unit,
    editTextValue: String,
    onEditTextChanged: (String) -> Unit,
    hint: String,
    keyboardIsVisible: Boolean,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
    ) {

        //create border from top here
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    height = borderHeight.dp
                )
                .background(
                    color = borderColor
                )
        )

        //create sub container here
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            //create ids for components here
            val (editTextId, fileButtonId, soundButtonId,
                cameraButtonId, sendButtonId) = createRefs()

            //create edit text message here
            com.example.presentation.uiElement.components.composable.EndIconEditTextView(
                dimen = dimen,
                theme = theme,
                icon = stickersIcon,
                onClickOnIcon = onClickOnStickerButton,
                value = editTextValue,
                onChanged = onEditTextChanged,
                hint = hint,
                modifier = Modifier
                    .constrainAs(editTextId) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_6_25.dp
                        )

                        //keyboard is visible add small padding to add send button only
                        if (editTextValue
                                .trim()
                                .isNotEmpty()
                        ) {

                            end.linkTo(
                                parent.end,
                                dimen.dimen_9_75.dp
                            )
                        }//end if

                        //keyboard is not visible add large padding
                        else {

                            end.linkTo(
                                parent.end,
                                dimen.dimen_11_5.dp
                            )
                        }//end else

                        top.linkTo(
                            parent.top,
                            dimen.dimen_2_25.dp
                        )
                        bottom.linkTo(
                            parent.bottom,
                            dimen.dimen_2_25.dp
                        )
                        width = Dimension.fillToConstraints
                    }
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 100,
                            easing = FastOutSlowInEasing
                        )
                    )
            )

            //create file button here
            IconButtonView(
                dimen = dimen,
                theme = theme,
                size = fileButtonSize,
                icon = fileIcon,
                tint = fileIconTint,
                onClick = onClickOnFileButton,
                modifier = Modifier
                    .constrainAs(fileButtonId) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        bottom.linkTo(
                            parent.bottom,
                            dimen.dimen_3_25.dp
                        )
                    }
            )

            //if keyboard is visible create send button
            if (editTextValue.trim().isNotEmpty()) {

                //create send button here
                FloatingActionButtonView(
                    icon = sendIcon,
                    iconSize = sendIconSize,
                    tint = sendIconTint,
                    floatingSize = sendButtonSize,
                    floatingColor = sendButtonBackground,
                    onClick = onClickOnSendButton,
                    modifier = Modifier
                        .constrainAs(sendButtonId) {
                            end.linkTo(
                                parent.end,
                                dimen.dimen_3.dp
                            )
                            bottom.linkTo(
                                parent.bottom,
                                dimen.dimen_2_25.dp
                            )
                        }
                )

            }//end if

            //if keyboard is not visible create sound and camera button
            else {

                //create sound button here
                IconButtonView(
                    dimen = dimen,
                    theme = theme,
                    size = soundButtonSize,
                    icon = soundIcon,
                    tint = soundIconTint,
                    onClick = onClickOnSoundButton,
                    modifier = Modifier
                        .constrainAs(soundButtonId) {
                            end.linkTo(
                                parent.end,
                                dimen.dimen_2.dp
                            )
                            bottom.linkTo(
                                parent.bottom,
                                dimen.dimen_3_25.dp
                            )
                        }
                )

                //create camera button here
                IconButtonView(
                    dimen = dimen,
                    theme = theme,
                    size = cameraButtonSize,
                    icon = cameraIcon,
                    tint = cameraIconTint,
                    onClick = onClickOnCameraButton,
                    modifier = Modifier
                        .constrainAs(cameraButtonId) {
                            end.linkTo(
                                soundButtonId.start,
                                dimen.dimen_1_5.dp
                            )
                            bottom.linkTo(soundButtonId.bottom)
                        }
                )

            }//end else

        }//end ConstraintLayout

    }//end Column

}//end ChatToolsSection