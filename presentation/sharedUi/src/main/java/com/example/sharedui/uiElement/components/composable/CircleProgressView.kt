package com.example.sharedui.uiElement.components.composable

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.material4.GradientCircularProgressBar
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun CircleProgressView(
    theme: CustomTheme,
    dimen: CustomDimen,
    size: Float = dimen.dimen_9,
    modifier: Modifier = Modifier
) {


    GradientCircularProgressBar(
        colors = Brush.linearGradient(
            colors = listOf(
                theme.redDark,
                theme.background
            )
        ),
        trackColor = Brush.verticalGradient(
            colors = listOf(
                theme.background,
                theme.background,
            )
        ),
        strokeWidth = dimen.dimen_2_5,
        modifier = modifier
            .size(
                size = size.dp
            )
    )
}//end CircleProgressView