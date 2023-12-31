@file:OptIn(ExperimentalAnimationApi::class)

package com.example.auth.uiElement.screens.register

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
import com.google.accompanist.navigation.animation.composable

const val REGISTER_DESTINATION_ROUTE = "registerDestination"

fun NavHostController.navigateToRegisterDestination() {

    navigate(
        route = REGISTER_DESTINATION_ROUTE
    )

}//end navigateToRegisterDestination

fun NavHostController.popRegisterDestination() {

    popBackStack(
        route = REGISTER_DESTINATION_ROUTE,
        inclusive = true
    )

}//end popRegisterDestination

fun NavGraphBuilder.registerDestination(
    popRegisterDestination: () -> Unit
) {

    composable(
        route = REGISTER_DESTINATION_ROUTE,
        enterTransition = { enterTransition() },
        popExitTransition = { exitTransition() }
    ) {

        RegisterScreen(
            popRegisterDestination = popRegisterDestination
        )

    }//end composable

}//end registerDestination


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