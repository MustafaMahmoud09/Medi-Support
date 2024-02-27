package com.example.reminder.uiElement.components.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.animation.animateColorAsState
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun DayView(
    dimen: CustomDimen,
    theme: CustomTheme,
    borderWidth: Float = dimen.dimen_0_125,
    unselectedBorderColor: Color = theme.visibleGray,
    selectedBorderColor: Color = theme.redDark,
    shape: Shape = RoundedCornerShape(
        size = (dimen.dimen_1_75 + dimen.dimen_0_125).dp
    ),
    selectedBackground: Color = theme.redDarkTR20,
    unselectedBackground: Color = theme.background,
    unselectedTextColor: Color = theme.black,
    selectedTextColor: Color = theme.redDark,
    dayNumber: Int,
    day: String,
    fontSize: Float = dimen.dimen_1_5,
    daysSelected: Array<Int>,
    width: Float = dimen.dimen_5_5,
    height: Float = dimen.dimen_7_75,
    modifier: Modifier = Modifier
) {

    //create animated border color here
    val borderColor by animateColorAsState(
        targetColor = if (dayNumber in daysSelected) {
            selectedBorderColor
        } else {
            unselectedBorderColor
        },
        label = "border color"
    )

    //create animated background color here
    val backgroundColor by animateColorAsState(
        targetColor = if (dayNumber in daysSelected) {
            selectedBackground
        } else {
            unselectedBackground
        },
        label = "background color"
    )

    //create animated day color here
    val dayColor by animateColorAsState(
        targetColor = if (dayNumber in daysSelected) {
            selectedTextColor
        } else {
            unselectedTextColor
        },
        label = "day color"
    )

    Box(
        modifier = modifier
            .appBorder(
                borderWidth = borderWidth,
                borderColor = borderColor,
                shape = shape
            )
            .width(
                width = width.dp
            )
            .height(
                height = height.dp
            )
            .background(
                color = backgroundColor
            ),
        contentAlignment = Alignment.Center
    ) {

        //create day text here
        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = day,
            size = fontSize,
            fontColor = dayColor
        )

    }//end Box

}//end DayView