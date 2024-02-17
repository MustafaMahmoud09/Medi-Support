package com.example.booking.uiElement.components.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun BookingTimeView(
    dimen: CustomDimen,
    theme: CustomTheme,
    shape: Shape = RoundedCornerShape(
        size = (dimen.dimen_0_125 + dimen.dimen_0_5).dp
    ),
    time: String = "08.00 AM",
    timeSize: Float = dimen.dimen_1_75,
    timeColor: Color = theme.black,
    unselectedBackground: Color = theme.grayF3F3F3,
    selectedBackground: Color = theme.background,
    borderWidth: Float = dimen.dimen_0_125,
    unselectedBorderColor: Color = theme.background,
    selectedBorderColor: Color = theme.redDark,
    modifier: Modifier = Modifier
) {

    //create container here
    Box(
        modifier = modifier
            .appBorder(
                borderWidth = borderWidth,
                borderColor = unselectedBorderColor,
                shape = shape
            )
            .background(
                color = unselectedBackground
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
            text = time,
            size = timeSize,
            fontColor = timeColor
        )

    }//end Box

}//end BookingTimeView