@file:OptIn(ExperimentalAnimationApi::class)

package com.example.auth.uiElement.screens.forgotten.email

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable

const val EMAIL_DESTINATION_ROUTE = "emailDestination"

fun NavGraphBuilder.emailDestination(
    navHostController: NavHostController,
    popForgotPasswordNavGraph: () -> Unit,
    navigateToCodeDestination: () -> Unit
) {

    composable(
        route = EMAIL_DESTINATION_ROUTE,
        exitTransition = { exitTransition() }
    ) {
        EmailScreen(
            navHostController = navHostController,
            popForgotPasswordNavGraph = popForgotPasswordNavGraph,
            navigateToCodeDestination = navigateToCodeDestination
        )
    }//end composable

}//end emailDestination

private fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition {

    return fadeOut(
        animationSpec = tween(
            durationMillis = 0
        )
    )

}//end existTransition
