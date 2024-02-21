@file:OptIn(ExperimentalAnimationApi::class)

package com.example.auth.uiElement.screens.forgotten.email


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.containers.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

const val EMAIL_DESTINATION_ROUTE = "emailDestination"

fun NavGraphBuilder.emailDestination(
    popForgotPasswordNavGraph: () -> Unit,
    navigateToCodeDestination: () -> Unit
) {

    composable(
        route = EMAIL_DESTINATION_ROUTE,
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() }
    ) {
        EmailScreen(
            popForgotPasswordNavGraph = popForgotPasswordNavGraph,
            navigateToCodeDestination = navigateToCodeDestination
        )
    }//end composable

}//end emailDestination