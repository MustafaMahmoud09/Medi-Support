package com.example.chat.uiElement.components.items

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.chat.uiElement.components.composable.CountView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun ChatSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_75.dp
    ),
    borderWidth: Float = dimen.dimen_0_125,
    borderColor: Color = theme.blackTR25,
    lastMessageSize: Float = dimen.dimen_1_5,
    lastMessageColor: Color = theme.grayA7A6A5,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .clickableWithoutHover {
                onClick(0)
            }
            .appBorder(
                shape = shape,
                borderWidth = borderWidth,
                borderColor = borderColor
            )
            .padding(
                bottom = dimen.dimen_1_75.dp
            )
    ) {
        //create ids for components here
        val (doctorProfileId, lastMessageTimeId, unseenMessagesNumberId, lastMessageId) = createRefs()

        //create last message time here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = "9:12",
            size = lastMessageSize,
            fontColor = lastMessageColor,
            modifier = Modifier
                .constrainAs(lastMessageTimeId) {
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1_75.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_2.dp
                    )
                }
        )

        //create doctor profile section here
        WorkerSection(
            dimen = dimen,
            theme = theme,
            modifier = Modifier
                .constrainAs(doctorProfileId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_1_5.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_2.dp
                    )
                    end.linkTo(
                        lastMessageTimeId.start,
                        dimen.dimen_1_5.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create unseen messages number here
        CountView(
            theme = theme,
            dimen = dimen,
            modifier = Modifier
                .constrainAs(unseenMessagesNumberId) {
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1_5.dp
                    )
                    top.linkTo(
                        doctorProfileId.bottom,
                        dimen.dimen_0_75.dp
                    )
                }
        )

        //create last message here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = "Vivamus varius odio non dui gravida, qui hello my bro in my love fuck you so mathch",
            size = dimen.dimen_1_5,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontColor = theme.grayA7A6A5,
            modifier = Modifier
                .constrainAs(lastMessageId) {
                    start.linkTo(
                        doctorProfileId.start,
                        dimen.dimen_5_75.dp
                    )
                    top.linkTo(unseenMessagesNumberId.top)
                    bottom.linkTo(unseenMessagesNumberId.bottom)
                    end.linkTo(
                        unseenMessagesNumberId.start,
                        dimen.dimen_1_5.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

    }//end ConstraintLayout

}//end ChatSection