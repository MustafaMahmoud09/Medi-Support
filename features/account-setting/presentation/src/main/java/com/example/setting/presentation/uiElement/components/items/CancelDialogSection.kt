@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.setting.presentation.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun CancelDialogSection(
    onDismissRequest: () -> Unit,
    dimen: CustomDimen,
    theme: CustomTheme,
    cancelTint: Color = theme.coolGray,
    iconSize: Float = dimen.dimen_3,
    boxLogoSize: Float = dimen.dimen_6,
    boxBorderSize: Float = dimen.dimen_0_125,
    boxTheme: Color = theme.redDark,
    onClickOnCancel: () -> Unit,
    logo: Painter,
    title: String,
    titleColor: Color = theme.coolGray,
    titleSize: Float = dimen.dimen_2_5,
    message: String,
    messageColor: Color = theme.coolGray,
    messageSize: Float = dimen.dimen_2,
    buttonTitle: String,
    onClick: () -> Unit,
    load: Boolean = false,
    modifier: Modifier = Modifier
) {


    AlertDialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        modifier = modifier
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                   horizontal = dimen.dimen_2.dp
                ),
            shape = RectangleShape
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = theme.background
                    ).padding(
                        bottom = dimen.dimen_2.dp
                    )
            ) {
                val (cancelButtonId, logoId, titleId, messageId, buttonId) = createRefs()

                Icon(
                    painter = painterResource(
                        id = R.drawable.cancel_icon
                    ),
                    contentDescription = "Icon",
                    tint = cancelTint,
                    modifier = Modifier
                        .constrainAs(cancelButtonId) {
                            top.linkTo(
                                parent.top,
                                dimen.dimen_2.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_2.dp
                            )
                        }
                        .size(
                            size = iconSize.dp
                        )
                        .clickable(
                            interactionSource = remember {
                                MutableInteractionSource()
                            },
                            indication = null
                        ) {
                            onClickOnCancel()
                        }//end clickable
                )

                Box(
                    modifier = Modifier
                        .constrainAs(logoId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(
                                parent.top,
                                dimen.dimen_2.dp
                            )
                        }
                        .size(
                            size = boxLogoSize.dp
                        )
                        .clip(
                            shape = RectangleShape
                        )
                        .border(
                            width = boxBorderSize.dp,
                            color = boxTheme,
                            shape = RectangleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        painter = logo,
                        contentDescription = "icon",
                        tint = boxTheme,
                        modifier = Modifier
                            .size(
                                size = iconSize.dp
                            )
                    )

                }//end Box

                TextBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = title,
                    size = titleSize,
                    color = titleColor,
                    modifier = Modifier
                        .constrainAs(titleId) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_2.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_2.dp
                            )
                            top.linkTo(
                                logoId.bottom,
                                dimen.dimen_2_5.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

                TextNormalView(
                    theme = theme,
                    dimen = dimen,
                    text = message,
                    size = messageSize,
                    fontColor = messageColor,
                    modifier = Modifier
                        .constrainAs(messageId) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_2.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_2.dp
                            )
                            top.linkTo(
                                titleId.bottom,
                                dimen.dimen_3.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

                BasicButtonView(
                    dimen = dimen,
                    theme = theme,
                    text = buttonTitle,
                    onClick = onClick,
                    load = load,
                    modifier = Modifier
                        .constrainAs(buttonId) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_2.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_2.dp
                            )
                            top.linkTo(
                                messageId.bottom,
                                dimen.dimen_4.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

            }//end ConstraintLayout

        }//end Card

    }//end AlertDialog

}//end CancelDialogSection