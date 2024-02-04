package com.example.sharedui.uiElement.components.modifier

import androidx.compose.foundation.border
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

//function for create border and clip to component in same time
@Stable
fun Modifier.appBorder(
    borderWidth: Float,
    borderColor: Color,
    shape: Shape
) = this.then(
    clip(
        shape = shape
    )
        .border(
            width = borderWidth.dp,
            color = borderColor,
            shape = shape
        )
)