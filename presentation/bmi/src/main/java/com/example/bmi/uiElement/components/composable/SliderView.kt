@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.bmi.uiElement.components.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun SliderView(
    dimen: CustomDimen,
    theme: CustomTheme,
    value: Float,
    onValueChange: (Float) -> Unit,
    colors: SliderColors = SliderDefaults.colors(
        activeTrackColor = theme.redDark,
        inactiveTrackColor = theme.background,
        thumbColor = theme.redDark,
    ),
    valueRange: ClosedFloatingPointRange<Float>,
    borderColor: Color = theme.redDark,
    trackShape: Shape = RoundedCornerShape(
        size = dimen.dimen_0_5.dp
    ),
    height: Float = dimen.dimen_2,
    trackBorderSize: Float = dimen.dimen_0_125,
    modifier: Modifier = Modifier
) {

    //create container here
    Box(
        modifier = modifier
            .height(
                height = height.dp
            ),
        contentAlignment = Alignment.Center
    ) {

        //create slider here
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            colors = colors,
            track = { sliderPosition ->
                //create track here
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(
                            height = dimen.dimen_1_5.dp
                        )
                        .appBorder(
                            borderWidth = trackBorderSize,
                            borderColor = borderColor,
                            shape = trackShape
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    SliderDefaults.Track(
                        sliderPositions = sliderPosition,
                        colors = colors,
                        modifier = Modifier
                            .scale(
                                scaleX = 1f,
                                scaleY = 3.3f
                            )
                    )

                }//end Box

            },//end track scope
            modifier = Modifier
                .fillMaxHeight(),
        )

    }//end Box


}//end SliderView





