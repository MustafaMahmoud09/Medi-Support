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
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun FloatingActionButtonView(
    dimen: CustomDimen,
    theme: CustomTheme,
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
        IconView(
            dimen = dimen,
            theme = theme,
            icon = icon,
            tint = tint,
            size = iconSize
        )

    }//end Box

}//end FloatingActionButtonView