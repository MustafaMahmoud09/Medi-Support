package com.example.sharedui.uiElement.animation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.snap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color

@Composable
fun animateColorAsState(
    targetColor: Color,
    label: String = "",
    animationSpec: AnimationSpec<Color> = snap()
): State<Color> {

    return animateColorAsState(
        targetValue = targetColor,
        label = label,
        animationSpec = animationSpec
    )
}