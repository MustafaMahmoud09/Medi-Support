@file:OptIn(ExperimentalAnimationApi::class)

package com.example.activity.uiElement.screens.activity

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.sharedui.uiElement.components.navigation.BottomDestination
import com.google.accompanist.navigation.animation.composable

val ACTIVITY_DESTINATION_DATA = BottomDestination(
    route = "activityDestination",
    icon = com.example.sharedui.R.drawable.activity,
    title = com.example.sharedui.R.string.activity
)

fun NavHostController.navigateToActivityDestination() {

    navigate(
        route = ACTIVITY_DESTINATION_DATA.route
    )

}//end navigateToActivityDestination

fun NavGraphBuilder.activityDestination() {

    composable(
        route = ACTIVITY_DESTINATION_DATA.route,
        enterTransition = { enterTransition() },
        popExitTransition = { exitTransition() }
    ) {

        ActivityScreen()

    }//end composable

}//end activityDestination


private fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition {

    return fadeIn(
        animationSpec = tween(
            durationMillis = 300
        )
    )

}//end enterTransition

private fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition {

    return fadeOut(
        animationSpec = tween(0)
    )

}//end existTransition