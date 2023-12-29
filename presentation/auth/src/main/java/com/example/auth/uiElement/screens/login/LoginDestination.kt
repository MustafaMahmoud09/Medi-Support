@file:OptIn(ExperimentalAnimationApi::class)

package com.example.auth.uiElement.screens.login

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
import com.google.accompanist.navigation.animation.composable

const val LOGIN_DESTINATION_ROUTE = "loginDestination"

fun NavGraphBuilder.loginDestination(
    navHostController: NavHostController,
    navigateToRegisterDestination: () -> Unit,
    navigateToForgotPasswordNavGraph: () -> Unit
) {

    composable(
        route = LOGIN_DESTINATION_ROUTE,
        exitTransition = { exitTransition() },
        popEnterTransition = { enterTransition() }
    ) {
        LoginScreen(
            navHostController = navHostController,
            navigateToRegisterDestination = navigateToRegisterDestination,
            navigateToForgotPasswordNavGraph = navigateToForgotPasswordNavGraph
        )
    }//end composable

}//end loginDestination


private fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition {

    return fadeIn(
        animationSpec = tween(
            durationMillis = 300
        )
    )


}//end enterTransition

private fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition {

    return fadeOut(
        animationSpec = tween(
            durationMillis = 0
        )
    )

}//end existTransition
