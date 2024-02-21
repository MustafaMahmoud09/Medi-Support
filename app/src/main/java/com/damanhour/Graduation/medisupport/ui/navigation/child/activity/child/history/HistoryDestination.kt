@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.activity.child.history

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.sharedui.uiElement.containers.navigation.enterTransitionZero
import com.example.sharedui.uiElement.containers.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

const val HISTORY_DESTINATION_ROUTE = "historyDestination"

fun NavHostController.navigateToHistoryDestination() {

    navigate(
        route = HISTORY_DESTINATION_ROUTE
    )

}//end navigateToHistoryDestination

fun NavHostController.popHistoryDestination() {

    popBackStack(
        route = HISTORY_DESTINATION_ROUTE,
        inclusive = true
    )

}//end popHistoryDestination

fun NavGraphBuilder.historyDestination(
    popHistoryDestination: () -> Unit
) {

    composable(
        route = HISTORY_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() }
    ) {

        HistoryScreen(
            popHistoryDestination = popHistoryDestination
        )
    }//end composable

}//end historyDestination
