package com.example.booking.uiElement.components.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun BookingDateView(
    dimen: CustomDimen,
    theme: CustomTheme,
    spacingBetweenComponents: Float = dimen.dimen_1,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    dayName: String = "Sat",
    dayNameSize: Float = dimen.dimen_1_75,
    unselectedDayNameColor: Color = theme.black,
    selectedDayNameColor: Color = theme.background,
    dayNumber: String = "9",
    dayNumberSize: Float = dimen.dimen_1_75,
    unselectedDayNumberColor: Color = theme.black,
    selectedDayNumberColor: Color = theme.background,
    unselectedBackground: Color = theme.grayF3F3F3,
    selectedBackground: Color = theme.redDark,
    modifier: Modifier = Modifier
) {

    //create container here
    Column(
        modifier = modifier
            .clip(
                shape = shape
            )
            .background(
                color = unselectedBackground
            )
            .padding(
                vertical = spacingBetweenComponents.dp,
                horizontal = dimen.dimen_2.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //create day number here
        TextBoldView(
            theme = theme,
            dimen = dimen,
            text = dayNumber,
            size = dayNumberSize,
            color = unselectedDayNumberColor
        )

        //create padding here
        Spacer(
            modifier = Modifier
                .height(
                    height = dimen.dimen_0_75.dp
                )
        )

        //create day number here
        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = dayName,
            size = dayNameSize,
            fontColor = unselectedDayNameColor
        )

    }//end Column

}//end BookingDateView