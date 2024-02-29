@file:OptIn(ExperimentalAnimationApi::class)

package com.example.auth.presentation.uiElement.screens.welcome

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.auth.presentation.uiElement.screens.start.START_DESTINATION_ROUTE
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionMain
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

const val WELCOME_DESTINATION_ROUTE = "welcomeDestination"

fun NavHostController.navigateToWelcomeDestination() {

    navigate(
        route = WELCOME_DESTINATION_ROUTE
    ) {

        popUpTo(
            route = com.example.auth.presentation.uiElement.screens.start.START_DESTINATION_ROUTE
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
        enterTransition = { enterTransitionMain() },
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
    ) {

        WelcomeScreen(
            navigateToLoginNavGraph = navigateToLoginNavGraph
        )

    }//end composable

}//end welcomeDestination
