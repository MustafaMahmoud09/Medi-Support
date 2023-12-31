@file:OptIn(ExperimentalAnimationApi::class)

package com.example.auth.uiElement.screens.forgotten.newPassword

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.auth.uiElement.screens.forgotten.code.CODE_DESTINATION_ROUTE
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
        enterTransition = { enterTransition() }
    ) {

        NewPasswordScreen(
            backToLoginNavGraph = backToLoginNavGraph
        )
    }//end composable

}//end newPasswordDestination

private fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition? {

    return when (initialState.destination.route) {

        CODE_DESTINATION_ROUTE -> {

            fadeIn(
                animationSpec = tween(
                    durationMillis = 300
                )
            )
        }//end case

        else -> null
    }//end when

}//end enterTransition