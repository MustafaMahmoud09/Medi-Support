package com.example.sharedui.uiElement.components.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover

@Composable
fun BasicIconView(
    painter: Painter,
    color: Color,
    size: Float,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {

    Icon(
        painter = painter,
        contentDescription = "icon",
        tint = color,
        modifier = modifier
            .size(size.dp)
            .clickableWithoutHover {
                onClick()
            }
    )

}//end BasicIconView