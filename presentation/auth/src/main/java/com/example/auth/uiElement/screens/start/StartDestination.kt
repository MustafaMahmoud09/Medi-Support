@file:OptIn(ExperimentalAnimationApi::class)

package com.example.auth.uiElement.screens.start

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.example.auth.uiElement.screens.welcome.WELCOME_DESTINATION_ROUTE
import com.google.accompanist.navigation.animation.composable

const val START_DESTINATION_ROUTE = "startDestination"

fun NavGraphBuilder.startDestination(
    navigateToWelcomeDestination: () -> Unit
) {

    composable(
        route = START_DESTINATION_ROUTE,
        exitTransition = {existTransition()}
    ) {

        StartScreen(
            navigateToWelcomeDestination = navigateToWelcomeDestination
        )

    }//end composable

}//end startDestination


private fun AnimatedContentScope<NavBackStackEntry>.existTransition(): ExitTransition? {

   return when (initialState.destination.route) {

        WELCOME_DESTINATION_ROUTE -> {

            fadeOut(
                animationSpec = tween(
                    durationMillis = 0
                )
            )

        }//end case
        else -> null
    }//end when

}//end existTransition