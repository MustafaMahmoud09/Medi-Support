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

@Composable
internal fun ChatDocumentSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    borderColor: Color = theme.black,
    borderHeight: Float = dimen.dimen_0_125,
    stickersIcon: Painter = painterResource(
        id = R.drawable.stickers_icon
    ),
    sendIcon: Painter = painterResource(
        id = R.drawable.send_icon
    ),
    sendIconSize: Float = dimen.dimen_2_25,
    sendIconTint: Color = theme.background,
    sendButtonSize: Float = dimen.dimen_5,
    sendButtonBackground: Color = theme.redDark,
    onClickOnStickerButton: () -> Unit,
    onClickOnSendButton: () -> Unit,
    editTextValue: String,
    onEditTextChanged: (String) -> Unit,
    hint: String,
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
            val (editTextId,sendButtonId) = createRefs()

            //create edit text message here
            EndIconEditTextView(
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
                            dimen.dimen_2.dp
                        )

                        end.linkTo(
                            parent.end,
                            dimen.dimen_8_75.dp
                        )

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

            //create send button here
            FloatingActionButtonView(
                dimen = dimen,
                theme = theme,
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
                            dimen.dimen_2.dp
                        )
                        bottom.linkTo(
                            parent.bottom,
                            dimen.dimen_2_25.dp
                        )
                    }
            )

        }//end ConstraintLayout

    }//end Column

}//end ChatToolsSection