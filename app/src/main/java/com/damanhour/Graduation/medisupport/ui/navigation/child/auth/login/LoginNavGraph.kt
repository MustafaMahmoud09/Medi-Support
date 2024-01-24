@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.forgotten.FORGOT_PASSWORD_NAV_GRAPH
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.forgotten.forgotPasswordNavGraph
import com.example.auth.uiElement.screens.login.LOGIN_DESTINATION_ROUTE
import com.example.auth.uiElement.screens.login.loginDestination
import com.example.auth.uiElement.screens.welcome.WELCOME_DESTINATION_ROUTE
import com.example.sharedui.uiElement.components.navigation.enterTransitionMain
import com.example.sharedui.uiElement.components.navigation.enterTransitionZero
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.navigation

internal const val LOGIN_NAV_GRAPH_ROUTE = "loginNavGraph"

internal fun NavHostController.navigateToLoginNavGraph() {

    navigate(
        route = LOGIN_NAV_GRAPH_ROUTE
    ) {

        popUpTo(
            route = WELCOME_DESTINATION_ROUTE
        ) {
            inclusive = true
        }//end popUpTo

    }//end navigate

}//end navigateToLoginNavGraph

internal fun NavHostController.backToLoginNavGraph() {

    navigate(
        route = LOGIN_NAV_GRAPH_ROUTE
    ) {

        popUpTo(
            route = FORGOT_PASSWORD_NAV_GRAPH
        ) {
            inclusive = true
        }//end popUpTo

    }//end navigate

}//end navigateToLoginNavGraph

internal fun NavGraphBuilder.loginNavGraph(
    navigateToRegisterDestination: () -> Unit,
    navigateToForgotPasswordNavGraph: () -> Unit,
    popForgotPasswordNavGraph: () -> Unit,
    navigateToCodeDestination: () -> Unit,
    navigateToNewPasswordDestination: () -> Unit,
    backToLoginNavGraph: () -> Unit,
    navigateToBottomDestination: () -> Unit
) {

    navigation(
        route = LOGIN_NAV_GRAPH_ROUTE,
        startDestination = LOGIN_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionMain() }
    ) {

        loginDestination(
            navigateToRegisterDestination = navigateToRegisterDestination,
            navigateToForgotPasswordNavGraph = navigateToForgotPasswordNavGraph,
            navigateToBottomDestination = navigateToBottomDestination
        )

        forgotPasswordNavGraph(
            popForgotPasswordNavGraph = popForgotPasswordNavGraph,
            navigateToCodeDestination = navigateToCodeDestination,
            navigateToNewPasswordDestination = navigateToNewPasswordDestination,
            backToLoginNavGraph = backToLoginNavGraph
        )

    }//end navigation

}//end loginNavGraph
