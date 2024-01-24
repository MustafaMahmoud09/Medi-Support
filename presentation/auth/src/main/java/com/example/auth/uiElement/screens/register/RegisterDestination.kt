@file:OptIn(ExperimentalAnimationApi::class)

package com.example.auth.uiElement.screens.register

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.sharedui.uiElement.components.navigation.enterTransitionMain
import com.example.sharedui.uiElement.components.navigation.enterTransitionZero
import com.example.sharedui.uiElement.components.navigation.exitTransition
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
        enterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() }
    ) {

        RegisterScreen(
            popRegisterDestination = popRegisterDestination
        )

    }//end composable

}//end registerDestination

