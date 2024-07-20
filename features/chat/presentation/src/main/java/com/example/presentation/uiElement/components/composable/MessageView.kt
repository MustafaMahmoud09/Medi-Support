package com.example.presentation.uiElement.components.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.chat.domain.model.MessageModel
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import kotlin.reflect.KFunction3

@Composable
internal fun MessageView(
    theme: CustomTheme,
    dimen: CustomDimen,
    background: Color,
    fileBackground: Color,
    message: MessageModel,
    messageColor: Color,
    messageSize: Float,
    messageShape: Shape,
    modifier: Modifier = Modifier,
    onOpenDownloadFile: KFunction3<String, String, String, Unit>,
    messageLoading: Boolean = false,
    backgroundLoading: Color = theme.redDarkTR50
) {

    if (message.attachment.ex.trim().isEmpty()) {

        //create container here
        Box(
            modifier = Modifier
                .clip(
                    shape = messageShape
                )
                .background(
                    color = if(!messageLoading) background else backgroundLoading
                )
                .padding(
                    all = dimen.dimen_1_5.dp
                ),
            contentAlignment = Alignment.Center,
        ) {

            //create message here
            TextNormalView(
                theme = theme,
                dimen = dimen,
                text = message.body,
                size = messageSize,
                textAlign = null,
                fontColor = messageColor,
            )

        }//end Box

    }//end if
    else {

        //create container here
        ConstraintLayout(
            modifier = modifier
                .clip(
                    shape = messageShape
                )
                .background(
                    color = if(!messageLoading) background else backgroundLoading
                )
                .padding(
                    horizontal = dimen.dimen_0_75.dp,
                    vertical = dimen.dimen_0_75.dp
                ),
        ) {
            //create ids for components here
            val (messageId, fileId) = createRefs()

            Row(
                modifier = Modifier
                    .constrainAs(fileId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        width = Dimension.fillToConstraints
                    }
                    .height(dimen.dimen_7_5.dp)
                    .clip(
                        shape = messageShape
                    )
                    .background(
                        color = if(!messageLoading) fileBackground else theme.redF04444TR50
                    )
                    .clickableWithoutHover {
                        if(!messageLoading) {
                            onOpenDownloadFile(
                                message.attachment.ex,
                                message.attachment.oldName,
                                message.attachment.newName
                            )
                        }//end if
                    }
                    .padding(
                        all = dimen.dimen_1.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                //create message here
                TextSemiBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = "${message.attachment.oldName}.${message.attachment.ex}",
                    size = messageSize,
                    textAlign = null,
                    fontColor = messageColor,
                )

            }//end Box

            if (message.body.trim().isNotEmpty()) {

                Box(
                    modifier = Modifier
                        .constrainAs(messageId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(
                                fileId.bottom,
                                dimen.dimen_1.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                        .padding(
                            bottom = dimen.dimen_0_75.dp,
                            start = dimen.dimen_0_75.dp,
                            end = dimen.dimen_0_75.dp
                        )
                ){

                    //create message here
                    TextNormalView(
                        theme = theme,
                        dimen = dimen,
                        text = message.body,
                        size = messageSize,
                        textAlign = null,
                        fontColor = messageColor,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                }//end Box

            }//end if
            else{

                Spacer(
                    modifier = Modifier
                        .constrainAs(messageId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(
                                fileId.bottom,
                                dimen.dimen_1_75.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

            }//end else

        }//end Box

    }//end else

}//end MessageView