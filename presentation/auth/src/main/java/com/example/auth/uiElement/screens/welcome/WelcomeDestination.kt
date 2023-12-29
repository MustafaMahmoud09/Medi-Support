@file:OptIn(ExperimentalAnimationApi::class)

package com.example.auth.uiElement.screens.welcome

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
import com.example.auth.uiElement.screens.start.START_DESTINATION_ROUTE
import com.google.accompanist.navigation.animation.composable

const val WELCOME_DESTINATION_ROUTE = "welcomeDestination"

fun NavHostController.navigateToWelcomeDestination() {

    navigate(
        route = WELCOME_DESTINATION_ROUTE
    ) {

        popUpTo(
            route = START_DESTINATION_ROUTE
        ) {
            inclusive = true
        }//end popUpTo

    }//end navigate

}//end navigateToWelcomeDestination


fun NavGraphBuilder.welcomeDestination(
    navigateToLoginNavGraph: () -> Unit
) {

    composable(
        route = WELCOME_DESTINATION_ROUTE,
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() }
    ) {

        WelcomeScreen(
            navigateToLoginNavGraph = navigateToLoginNavGraph
        )

    }//end composable

}//end welcomeDestination


private fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition? {

    return when (initialState.destination.route) {

        START_DESTINATION_ROUTE -> {

            fadeIn(
                animationSpec = tween(
                    durationMillis = 300
                )
            )
        }//end case

        else -> null
    }//end when

}//end enterTransition

private fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition {

    return fadeOut(
        animationSpec = tween(
            durationMillis = 0
        )
    )

}//end existTransition