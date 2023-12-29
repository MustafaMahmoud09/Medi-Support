@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.bmi.uiElement.components.composable


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme


@Composable
internal fun SliderView(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    colors: SliderColors = SliderDefaults.colors(
        activeTrackColor = Color(0xFF939393),
        inactiveTrackColor = Color(0xFFD9D9D9),
        thumbColor = Color(0xFF939393),
    ),
    valueRange: ClosedFloatingPointRange<Float> = 0f..100f,
    modifier: Modifier = Modifier
) {
    var state by remember { mutableStateOf(0f) }

    Slider(
        modifier = modifier,
        value = state,
        onValueChange = { state = it },
        valueRange = valueRange,
        colors = colors,
        track = {

        }//end track
    )
}//end SliderView


