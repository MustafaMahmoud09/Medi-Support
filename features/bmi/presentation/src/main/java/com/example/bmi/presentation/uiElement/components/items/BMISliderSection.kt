package com.example.bmi.presentation.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.bmi.presentation.uiElement.components.composable.SliderView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun BMISliderSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    title: String,
    titleSize: Float = dimen.dimen_2_25,
    titleColor: Color = theme.black,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueSize: Float = dimen.dimen_2_25,
    valueColor: Color = theme.blackLight353535,
    unit: String,
    startPoint: Int,
    endPoint: Int,
    pointsColor: Color = theme.grayD7D7D7,
    pointsSize: Float = dimen.dimen_2_25,
    modifier: Modifier = Modifier
) {

    //create container here
    Column(
        modifier = modifier
    ) {

        //create title box here
        Box(
            modifier = Modifier
                .padding(
                    start = dimen.dimen_1_25.dp
                )
        ) {
            //create title text here
            TextSemiBoldView(
                theme = theme,
                dimen = dimen,
                text = "$title($unit)",
                size = titleSize,
                fontColor = titleColor
            )

        }//end Box

        //create value selected box here
        Box(
            modifier
                .padding(
                    start = dimen.dimen_1_25.dp,
                    top = dimen.dimen_1_5.dp
                )
        ) {

            //create value selected text here
            TextSemiBoldView(
                theme = theme,
                dimen = dimen,
                text = "${value.toInt()}",
                size = valueSize,
                fontColor = valueColor,
            )

        }//end Box

        //create slider box here
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimen.dimen_0_25.dp,
                    end = dimen.dimen_0_25.dp,
                    top = dimen.dimen_1.dp
                )
        ) {

            //create slider here
            com.example.bmi.presentation.uiElement.components.composable.SliderView(
                dimen = dimen,
                theme = theme,
                value = value,
                onValueChange = onValueChange,
                valueRange = startPoint.toFloat()..endPoint.toFloat(),
                modifier = Modifier
                    .fillMaxWidth()
            )

        }//end Box

        //create hint row here
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimen.dimen_1_25.dp,
                    end = dimen.dimen_1_25.dp,
                    top = dimen.dimen_2.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            //create start point text here
            TextSemiBoldView(
                theme = theme,
                dimen = dimen,
                text = "$startPoint",
                size = pointsSize,
                fontColor = pointsColor,
                modifier = Modifier
            )

            //create end point text here
            TextSemiBoldView(
                theme = theme,
                dimen = dimen,
                text = "$endPoint",
                size = pointsSize,
                fontColor = pointsColor,
                modifier = Modifier
            )

        }//end Row

    }//end ConstraintLayout

}//end GenderSliderSection