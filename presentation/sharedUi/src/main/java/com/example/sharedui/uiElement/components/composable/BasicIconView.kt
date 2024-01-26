package com.example.sharedui.uiElement.components.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun BasicIconView(
    painter: Painter,
    color: Color,
    size: Float,
    modifier: Modifier = Modifier,
) {

    Icon(
        painter = painter,
        contentDescription = "icon",
        tint = color,
        modifier = modifier
            .size(size.dp)
    )

}//end BasicIconView