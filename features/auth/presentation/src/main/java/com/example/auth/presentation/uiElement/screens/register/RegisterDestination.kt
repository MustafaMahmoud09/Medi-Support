@file:OptIn(ExperimentalAnimationApi::class)

package com.example.auth.presentation.uiElement.screens.register

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionMain
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
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
    popRegisterDestination: () -> Unit,
    navigateToBottomDestination: () -> Unit
) {

    composable(
        route = REGISTER_DESTINATION_ROUTE,
        enterTransition = { enterTransitionMain() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
    ) {

        RegisterScreen(
            popRegisterDestination = popRegisterDestination,
            navigateToBottomDestination = navigateToBottomDestination
        )

    }//end composable

}//end registerDestination

