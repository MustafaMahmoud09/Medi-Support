package com.example.sharedui.uiElement.animation

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.snap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

@Composable
fun animateFloatAsState(
    targetFloat: Float,
    label: String = "",
    animationSpec: AnimationSpec<Float> = snap()
): State<Float> {

    return androidx.compose.animation.core.animateFloatAsState(
        targetValue = targetFloat,
        label = label,
        animationSpec = animationSpec
    )
}