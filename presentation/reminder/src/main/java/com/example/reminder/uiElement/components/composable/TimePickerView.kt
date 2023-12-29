@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.reminder.uiElement.components.composable

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
fun TimePickerView(
    state: TimePickerState = rememberTimePickerState(),
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    colors: TimePickerColors = TimePickerDefaults.colors(
        clockDialColor = Color.Red,
        clockDialSelectedContentColor = Color.Green,
        clockDialUnselectedContentColor = Color.Yellow,
        selectorColor = Color.Gray,
        periodSelectorBorderColor = Color.White,
        periodSelectorSelectedContainerColor = Color.Red,
        periodSelectorUnselectedContainerColor = Color.Gray,
        periodSelectorSelectedContentColor = Color.White,
        periodSelectorUnselectedContentColor = Color.Blue,
        timeSelectorSelectedContainerColor = Color.Black,
        timeSelectorUnselectedContainerColor = Color.Red,
        timeSelectorSelectedContentColor = Color.White,
        timeSelectorUnselectedContentColor = Color.Blue
    ),
    modifier: Modifier = Modifier
) {

    TimePicker(
        state = state,
        colors = colors,
        modifier = modifier
    )
}//end TimePickerView