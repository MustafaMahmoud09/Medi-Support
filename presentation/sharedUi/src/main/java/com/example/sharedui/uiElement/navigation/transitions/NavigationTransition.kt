@file:OptIn(ExperimentalAnimationApi::class)

package com.example.sharedui.uiElement.navigation.transitions

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry


fun AnimatedContentScope<NavBackStackEntry>.enterTransitionZero(): EnterTransition {

    return fadeIn(
        animationSpec = tween(
            durationMillis = 0
        )
    )

}//end enterTransitionZero

fun AnimatedContentScope<NavBackStackEntry>.enterTransitionMain(): EnterTransition {

    return fadeIn(
        animationSpec = tween(
            durationMillis = 300
        )
    )

}//end enterTransitionMain

fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition {

    return fadeOut(
        animationSpec = tween(
            durationMillis = 0
        )
    )

}//end enterTransition