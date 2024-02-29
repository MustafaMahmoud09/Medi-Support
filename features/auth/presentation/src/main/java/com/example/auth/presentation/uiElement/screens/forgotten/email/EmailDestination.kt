@file:OptIn(ExperimentalAnimationApi::class)

package com.example.auth.presentation.uiElement.screens.forgotten.email

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

const val EMAIL_DESTINATION_ROUTE = "emailDestination"

fun NavGraphBuilder.emailDestination(
    popForgotPasswordNavGraph: () -> Unit,
    navigateToCodeDestination: () -> Unit
) {

    composable(
        route = EMAIL_DESTINATION_ROUTE,
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() },
        enterTransition = { enterTransitionZero() },
        popEnterTransition = { enterTransitionZero() },
    ) {
        EmailScreen(
            popForgotPasswordNavGraph = popForgotPasswordNavGraph,
            navigateToCodeDestination = navigateToCodeDestination
        )
    }//end composable

}//end emailDestination