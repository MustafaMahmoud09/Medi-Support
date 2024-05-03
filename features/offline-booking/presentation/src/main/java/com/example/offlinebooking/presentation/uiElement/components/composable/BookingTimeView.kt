package com.example.offlinebooking.presentation.uiElement.components.composable

import androidx.compose.animation.core.snap
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.offline.booking.domain.model.TimeModel
import com.example.sharedui.uiElement.animation.animateColorAsState
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.google.accompanist.placeholder.placeholder

@Composable
internal fun BookingTimeView(
    dimen: CustomDimen,
    theme: CustomTheme,
    shape: Shape = RoundedCornerShape(
        size = (dimen.dimen_0_125 + dimen.dimen_0_5).dp
    ),
    timeSize: Float = dimen.dimen_1_75,
    timeColor: Color = theme.black,
    unselectedBackground: Color = theme.grayF3F3F3,
    selectedBackground: Color = theme.background,
    borderWidth: Float = dimen.dimen_0_125,
    unselectedBorderColor: Color = theme.background,
    selectedBorderColor: Color = theme.redDark,
    placeHolderState: Boolean = false,
    placeHolderColor: Color = theme.grayLight,
    timeSelectedId: Long,
    timeModel: TimeModel,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {

    val background by animateColorAsState(
        targetColor = if (timeSelectedId != timeModel.id) {
            unselectedBackground
        } else {
            selectedBackground
        },
        animationSpec = snap(
            delayMillis = 50
        )
    )

    val borderColor by animateColorAsState(
        targetColor = if (timeSelectedId != timeSelectedId) {
            unselectedBorderColor
        } else {
            selectedBorderColor
        }
    )

    //create container here
    Box(
        modifier = modifier
            .clip(
                shape = shape
            )
            .placeholder(
                visible = placeHolderState,
                color = placeHolderColor
            )
            .border(
                width = borderWidth.dp,
                color = borderColor,
                shape = shape
            )
            .clickableWithoutHover {
                onClick(timeModel.id)
            }
            .background(
                color = background
            )
            .padding(
                vertical = dimen.dimen_1.dp,
                horizontal = dimen.dimen_1_25.dp
            ),
        contentAlignment = Alignment.Center
    ) {

        //create time here
        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = timeModel.time,
            size = timeSize,
            fontColor = timeColor
        )

    }//end Box

}//end BookingTimeView