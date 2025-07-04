package com.example.sharedui.uiElement.components.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun IconView(
    icon: Painter,
    dimen: CustomDimen,
    theme: CustomTheme,
    size: Float = dimen.dimen_3_5,
    tint: Color = theme.black,
    modifier: Modifier = Modifier
) {

    Icon(
        painter = icon,
        tint = tint,
        contentDescription = "back_icon",
        modifier = modifier
            .size(
                size = size.dp
            )
    )

}//end IconView