package com.example.sharedui.uiElement.components.modifier

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

//function for create create shadow and clip item in same time
@Stable
fun Modifier.appShadow(
    shape: Shape,
    elevation: Float,
    clip: Boolean = elevation > 0,
    ambientColor: Color,
    spotColor: Color,
) = then(
    shadow(
        elevation = elevation.dp,
        clip = clip,
        ambientColor = ambientColor,
        spotColor = spotColor
    ).clip(
        shape = shape
    )
)