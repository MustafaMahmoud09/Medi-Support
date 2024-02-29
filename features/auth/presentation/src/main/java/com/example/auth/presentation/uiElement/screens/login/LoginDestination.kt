@file:OptIn(ExperimentalAnimationApi::class)

package com.example.auth.presentation.uiElement.screens.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionMain
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

const val LOGIN_DESTINATION_ROUTE = "loginDestination"

fun NavGraphBuilder.loginDestination(
    navigateToRegisterDestination: () -> Unit,
    navigateToForgotPasswordNavGraph: () -> Unit,
    navigateToBottomDestination: () -> Unit
) {

    composable(
        route = LOGIN_DESTINATION_ROUTE,
        exitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionMain() },
        enterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() },
    ) {
        LoginScreen(
            navigateToRegisterDestination = navigateToRegisterDestination,
            navigateToForgotPasswordNavGraph = navigateToForgotPasswordNavGraph,
            navigateToBottomDestination = navigateToBottomDestination
        )
    }//end composable

}//end loginDestination


