@file:OptIn(ExperimentalAnimationApi::class)

package com.example.auth.presentation.uiElement.screens.forgotten.code

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.auth.presentation.uiElement.screens.forgotten.email.EMAIL_DESTINATION_ROUTE
import com.example.auth.presentation.uiElement.screens.login.LOGIN_DESTINATION_ROUTE
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

const val CODE_DESTINATION_ROUTE = "codeDestination"

fun NavHostController.navigateToCodeDestination() {

    navigate(
        route = CODE_DESTINATION_ROUTE
    ) {

        popUpTo(
            route = EMAIL_DESTINATION_ROUTE
        ) {
            inclusive = true
        }//end popUpTo

        popUpTo(
            route = LOGIN_DESTINATION_ROUTE
        ) {
            inclusive = true
        }//end popUpTo

    }//end navigate

}//end navigateToCodeDestination

fun NavGraphBuilder.codeDestination(
    navigateToNewPasswordDestination: () -> Unit
) {

    composable(
        route = CODE_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
    ) {
        CodeScreen(
            navigateToNewPasswordDestination = navigateToNewPasswordDestination
        )
    }
}//end codeDestination