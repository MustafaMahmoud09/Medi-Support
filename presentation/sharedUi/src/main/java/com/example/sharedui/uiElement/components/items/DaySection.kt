package com.example.sharedui.uiElement.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun DaySection(
    dimen: CustomDimen,
    dayName: String,
    dayNumber: String,
    theme: CustomTheme,
    shape: Shape = CircleShape,
    borderWidth: Float = dimen.dimen_0_125 + dimen.dimen_0_075,
    borderColor: Color = theme.redDarkTR50,
    textDayColor: Color = theme.black,
    textDaySize: Float = dimen.dimen_1_75,
    modifier: Modifier = Modifier
) {

    //create container here
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        //create day name here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = dayName,
            size = textDaySize,
            fontColor = textDayColor
        )

        //create padding
        Spacer(
            modifier = Modifier
                .height(
                    dimen.dimen_1.dp
                )
        )

        //create container to day name number here
        Box(
            modifier = Modifier
                .size(
                    dimen.dimen_5_5.dp
                )
                .appBorder(
                    shape = shape,
                    borderWidth = borderWidth,
                    borderColor = borderColor,
                ),
            contentAlignment = Alignment.Center
        ) {

            //create day number here
            TextSemiBoldView(
                theme = theme,
                dimen = dimen,
                text = dayNumber,
                size = textDaySize,
                fontColor = textDayColor
            )

        }//end Box

    }//end Column

}//end DaySection
