@file:OptIn(ExperimentalAnimationApi::class)

package com.example.auth.uiElement.screens.start

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

const val START_DESTINATION_ROUTE = "startDestination"

fun NavGraphBuilder.startDestination(
    navigateToWelcomeDestination: () -> Unit
) {

    composable(
        route = START_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = {exitTransition()},
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
    ) {

        StartScreen(
            navigateToWelcomeDestination = navigateToWelcomeDestination
        )

    }//end composable

}//end startDestination

