@file:OptIn(ExperimentalAnimationApi::class)

package com.example.auth.uiElement.screens.start

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

const val START_DESTINATION_ROUTE = "startDestination"

fun NavGraphBuilder.startDestination(
    navigateToWelcomeDestination: () -> Unit
) {

    composable(
        route = START_DESTINATION_ROUTE,
        exitTransition = {exitTransition()}
    ) {

        StartScreen(
            navigateToWelcomeDestination = navigateToWelcomeDestination
        )

    }//end composable

}//end startDestination

