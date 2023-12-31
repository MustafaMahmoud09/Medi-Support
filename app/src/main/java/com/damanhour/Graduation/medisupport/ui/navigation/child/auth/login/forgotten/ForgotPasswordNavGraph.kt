@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.forgotten

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
import com.example.auth.uiElement.screens.forgotten.code.codeDestination
import com.example.auth.uiElement.screens.forgotten.email.EMAIL_DESTINATION_ROUTE
import com.example.auth.uiElement.screens.forgotten.email.emailDestination
import com.example.auth.uiElement.screens.forgotten.newPassword.newPasswordDestination
import com.example.auth.uiElement.screens.login.LOGIN_DESTINATION_ROUTE
import com.google.accompanist.navigation.animation.navigation

internal const val FORGOT_PASSWORD_NAV_GRAPH = "forgotPasswordNavGraph"

internal fun NavHostController.navigateToForgotPasswordNavGraph() {

    navigate(
        route = FORGOT_PASSWORD_NAV_GRAPH
    )
}//end navigateToForgotPasswordNavGraph

internal fun NavHostController.popForgotPasswordNavGraph() {

    popBackStack(
        route = FORGOT_PASSWORD_NAV_GRAPH,
        inclusive = true
    )
}//end popForgotPasswordNavGraph

internal fun NavGraphBuilder.forgotPasswordNavGraph(
    popForgotPasswordNavGraph: () -> Unit,
    navigateToCodeDestination: () -> Unit,
    navigateToNewPasswordDestination: () -> Unit,
    backToLoginNavGraph: () -> Unit
) {

    navigation(
        route = FORGOT_PASSWORD_NAV_GRAPH,
        startDestination = EMAIL_DESTINATION_ROUTE,
        enterTransition = { enterTransition() },
        popExitTransition = { exitTransition() }
    ) {

        emailDestination(
            popForgotPasswordNavGraph = popForgotPasswordNavGraph,
            navigateToCodeDestination = navigateToCodeDestination
        )

        codeDestination(
            navigateToNewPasswordDestination = navigateToNewPasswordDestination
        )

        newPasswordDestination(
            backToLoginNavGraph = backToLoginNavGraph
        )

    }
}//end forgotPasswordNavGraph


private fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition? {

    return when (initialState.destination.route) {

        LOGIN_DESTINATION_ROUTE -> {

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