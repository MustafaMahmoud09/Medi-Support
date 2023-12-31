@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.forgotten.FORGOT_PASSWORD_NAV_GRAPH
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.forgotten.forgotPasswordNavGraph
import com.example.auth.uiElement.screens.login.LOGIN_DESTINATION_ROUTE
import com.example.auth.uiElement.screens.login.loginDestination
import com.example.auth.uiElement.screens.register.REGISTER_DESTINATION_ROUTE
import com.example.auth.uiElement.screens.welcome.WELCOME_DESTINATION_ROUTE
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
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { enterTransition() }
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


private fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition? {

    return when (initialState.destination.route) {

        WELCOME_DESTINATION_ROUTE -> {

            fadeIn(
                animationSpec = tween(
                    durationMillis = 300
                )
            )
        }//end case

        REGISTER_DESTINATION_ROUTE -> {

            fadeIn(
                animationSpec = tween(
                    durationMillis = 300
                )
            )
        }//end case
        else -> null
    }//end when

}//end enterTransition


private fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition {

    return fadeOut(
        animationSpec = tween(0)
    )

}//end existTransition