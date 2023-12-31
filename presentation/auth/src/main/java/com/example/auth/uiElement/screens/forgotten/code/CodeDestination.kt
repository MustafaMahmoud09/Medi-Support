@file:OptIn(ExperimentalAnimationApi::class)

package com.example.auth.uiElement.screens.forgotten.code

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
import com.example.auth.uiElement.screens.forgotten.email.EMAIL_DESTINATION_ROUTE
import com.example.auth.uiElement.screens.login.LOGIN_DESTINATION_ROUTE
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
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() }
    ) {
        CodeScreen(
            navigateToNewPasswordDestination = navigateToNewPasswordDestination
        )
    }
}//end codeDestination

private fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition? {

    return when (initialState.destination.route) {

        EMAIL_DESTINATION_ROUTE -> {

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
        animationSpec = tween(
            durationMillis = 0
        )
    )

}//end existTransition