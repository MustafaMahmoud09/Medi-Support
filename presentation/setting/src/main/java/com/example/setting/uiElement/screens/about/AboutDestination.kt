@file:OptIn(ExperimentalAnimationApi::class)

package com.example.setting.uiElement.screens.about

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

const val ABOUT_DESTINATION_ROUTE = "aboutDestination"

fun NavHostController.navigateToAboutDestination() {

    navigate(
        route = ABOUT_DESTINATION_ROUTE
    )

}//end navigateToAboutDestination

fun NavHostController.popAboutDestination() {

    popBackStack(
        route = ABOUT_DESTINATION_ROUTE,
        inclusive = true
    )

}//end popAboutDestination

fun NavGraphBuilder.aboutDestination(
    popAboutDestination: () -> Unit
) {

    composable(
        route = ABOUT_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
    ) {

        AboutScreen(
            popAboutDestination = popAboutDestination
        )
    }//end composable

}//end aboutDestination