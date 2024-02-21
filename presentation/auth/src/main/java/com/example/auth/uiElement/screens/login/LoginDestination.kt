@file:OptIn(ExperimentalAnimationApi::class)

package com.example.auth.uiElement.screens.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.containers.navigation.enterTransitionMain
import com.example.sharedui.uiElement.containers.navigation.exitTransition
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
        popEnterTransition = { enterTransitionMain() }
    ) {
        LoginScreen(
            navigateToRegisterDestination = navigateToRegisterDestination,
            navigateToForgotPasswordNavGraph = navigateToForgotPasswordNavGraph,
            navigateToBottomDestination = navigateToBottomDestination
        )
    }//end composable

}//end loginDestination


