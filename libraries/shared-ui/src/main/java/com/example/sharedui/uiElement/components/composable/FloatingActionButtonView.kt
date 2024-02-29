package com.example.sharedui.uiElement.components.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.modifier.appShadow
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover

@Composable
fun FloatingActionButtonView(
    icon: Painter,
    iconSize: Float,
    tint: Color,
    floatingSize: Float,
    floatingColor: Color,
    shape: Shape = CircleShape,
    onClick: () -> Unit,
    elevation: Float = 0f,
    shadowColor: Color = Color.White,
    modifier: Modifier = Modifier,
) {

    //create container here
    Box(
        modifier = modifier
            .size(
                size = floatingSize.dp
            )
            .appShadow(
                shape = shape,
                elevation = elevation,
                spotColor = shadowColor,
                ambientColor = shadowColor
            )
            .background(
                color = floatingColor
            )
            .clickableWithoutHover {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {

        //create icon here
        BasicIconView(
            painter = icon,
            color = tint,
            size = iconSize
        )

    }//end Box

}//end FloatingActionButtonView