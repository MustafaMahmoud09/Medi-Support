@file:OptIn(ExperimentalAnimationApi::class)

package com.example.auth.uiElement.screens.forgotten.newPassword

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.auth.uiElement.screens.forgotten.code.CODE_DESTINATION_ROUTE
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

const val NEW_PASSWORD_DESTINATION_ROUTE = "newPasswordDestination"

fun NavHostController.navigateToNewPasswordDestination() {

    navigate(
        route = NEW_PASSWORD_DESTINATION_ROUTE
    ) {

        popUpTo(
            route = CODE_DESTINATION_ROUTE
        ) {
            inclusive = true
        }//end popUpTo

    }//end navigate

}//end navigateToNewPasswordDestination

fun NavGraphBuilder.newPasswordDestination(
    backToLoginNavGraph: () -> Unit
) {

    composable(
        route = NEW_PASSWORD_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
    ) {

        NewPasswordScreen(
            backToLoginNavGraph = backToLoginNavGraph
        )
    }//end composable

}//end newPasswordDestination
