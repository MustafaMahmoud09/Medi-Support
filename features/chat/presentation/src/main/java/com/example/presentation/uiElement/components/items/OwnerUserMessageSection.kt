package com.example.presentation.uiElement.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.presentation.uiElement.components.composable.MessageView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun OwnerUserMessageSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    messageShape: Shape = RoundedCornerShape(
        bottomEnd = dimen.dimen_2.dp,
        topEnd = dimen.dimen_0.dp,
        bottomStart = dimen.dimen_2.dp,
        topStart = dimen.dimen_2.dp
    ),
    isEndMessage: Boolean = false,
    isEndChatMessage: Boolean = false,
    background: Color = theme.redDark,
    message: String,
    messageColor: Color = theme.background,
    messageSize: Float = dimen.dimen_1_5,
    timeColor: Color = theme.gray797C7B,
    timeSize: Float = dimen.dimen_1_25,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .padding(
                bottom = //if message is end chat message create padding from bottom equal 0
                if (isEndChatMessage) {
                    dimen.dimen_0.dp
                }//end if
                //if message is not end message create padding from bottom equal 6
                else if (!isEndMessage) {
                    dimen.dimen_0_75.dp
                }//end else
                else {
                    dimen.dimen_2.dp
                }//else
            )
    ) {
        //create ids for components here
        val (messageId, timeId) = createRefs()

        //create guides for components here
        val guideLineFromEnd73P = createGuidelineFromEnd(0.73f)

        //create message box here
        Row(
            modifier = Modifier
                .constrainAs(messageId) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    start.linkTo(guideLineFromEnd73P)
                    width = Dimension.fillToConstraints
                },
            horizontalArrangement = Arrangement.End
        ) {

            //create message view here
            com.example.presentation.uiElement.components.composable.MessageView(
                theme = theme,
                dimen = dimen,
                background = background,
                message = message,
                messageColor = messageColor,
                messageSize = messageSize,
                messageShape = messageShape
            )

        }//end Row

        //if this message is end message create text to show time
        if (isEndMessage) {

            //create text time here
            TextBoldView(
                theme = theme,
                dimen = dimen,
                text = "09:25 AM",
                size = timeSize,
                color = timeColor,
                modifier = Modifier
                    .constrainAs(timeId) {
                        end.linkTo(
                            parent.end,
                            dimen.dimen_1.dp
                        )
                        top.linkTo(
                            messageId.bottom,
                            dimen.dimen_1.dp
                        )
                    }
            )

        }//end if

    }//end ConstraintLayout

}//end OwnerUserMessageSection