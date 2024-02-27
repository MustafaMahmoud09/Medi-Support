@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.sharedui.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.BasicIconView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun MessagesDialogSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    logo: Painter,
    logoTint: Color,
    shape: Shape = RoundedCornerShape(
        dimen.dimen_1_25.dp
    ),
    title: String,
    titleColor: Color = theme.black,
    titleSize: Float = dimen.dimen_2_5,
    onDismissRequest: () -> Unit = {},
    logoPaddingTop: Float = dimen.dimen_8_5,
    logoSize: Float = dimen.dimen_10,
    titlePaddingTop: Float = dimen.dimen_2_5,
    messagesPaddingTop: Float = dimen.dimen_2_5,
    messages: Array<DialogMessage> = arrayOf(),
    buttonTitle: String,
    buttonTitleSize: Float,
    buttonTitleColor: Color,
    buttonBackground: Color,
    onClickOnButton: () -> Unit,
    buttonPaddingTop: Float = dimen.dimen_2_5,
    paddingBottom: Float = dimen.dimen_3,
    horizontalMargin: Float,
    modifier: Modifier = Modifier,
) {

    //create alert dialog here
    AlertDialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        modifier = modifier
    ) {

        //create card here
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    horizontal = horizontalMargin.dp
                ),
            shape = shape
        ) {

            //create container contain on components here
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = theme.background
                    ).padding(
                        bottom = paddingBottom.dp
                    )
            ) {
                val (successLogoId, titleId, messagesId, buttonId) = createRefs()

                //create success logo here
                BasicIconView(
                    painter = logo,
                    color = logoTint,
                    size = logoSize,
                    modifier = Modifier
                        .constrainAs(successLogoId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(
                                parent.top,
                                logoPaddingTop.dp
                            )
                        }
                )

                //create title here
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
                                successLogoId.bottom,
                                titlePaddingTop.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

                //create messages section here
                Column(
                    modifier = Modifier
                        .constrainAs(messagesId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(
                                titleId.bottom,
                                messagesPaddingTop.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                ) {

                    //for loop on messages here
                    messages.forEach { message ->

                        //create padding top to message here
                        Spacer(
                            modifier = Modifier
                                .height(
                                    height = message.paddingTop.dp
                                )
                        )

                        //create message box here
                        Box(
                            modifier = Modifier
                                .padding(
                                    horizontal = message.paddingHorizontal.dp
                                )
                        ) {

                            //create message here
                            TextNormalView(
                                theme = theme,
                                dimen = dimen,
                                text = message.message,
                                size = message.size,
                                fontColor = message.color,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                        }//end box

                    }//end for each

                }//end Column

                //create button here
                BasicButtonView(
                    dimen = dimen,
                    theme = theme,
                    text = buttonTitle,
                    onClick = onClickOnButton,
                    fontColor = buttonTitleColor,
                    fontSize = buttonTitleSize,
                    color = buttonBackground,
                    modifier = Modifier
                        .constrainAs(buttonId) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_2_75.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_2_75.dp
                            )
                            top.linkTo(
                                messagesId.bottom,
                                buttonPaddingTop.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

            }//end ConstraintLayout

        }//end Card

    }//end Dialog

}//end SuccessfullyBookingDialogSection


data class DialogMessage(
    val message: String,
    val color: Color,
    val size: Float,
    val paddingTop: Float,
    val paddingHorizontal: Float,
)