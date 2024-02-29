package com.example.sharedui.uiElement.components.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


//function for create create shadow and clip item in same time
@Stable
fun Modifier.appDefaultContainer(
    color: Color
) = then(
    fillMaxSize()
        .safeDrawingPadding()
        .background(
            color = color
        )
)