@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.activity.ACTIVITY_NAV_GRAPH_DATA
import com.example.heartrate.presentation.uiElement.screens.measurement.MEASUREMENT_HEART_RATE_DESTINATION_ROUTE
import com.example.heartrate.presentation.uiElement.screens.measurement.measurementHeartRateDestination
import com.example.heartrate.presentation.uiElement.screens.statistics.statisticsHeartRateDestination
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.navigation

internal const val HEART_RATE_NAV_GRAPH = "heartRateNavGraph"

internal fun NavHostController.navigateToHeartRateNavGraph() {

    navigate(
        route = HEART_RATE_NAV_GRAPH
    )

}//end navigateToHeartRateNavGraph

internal fun NavHostController.navigateToHeartRateNavGraphWithPopActivityNavGraph() {

    navigate(
        route = HEART_RATE_NAV_GRAPH
    ) {

        popUpTo(
            route = ACTIVITY_NAV_GRAPH_DATA.route
        ) {

            inclusive = true

        }//end popUpTo

    }//end navigate

}//end navigateToHeartRateNavGraph

internal fun NavGraphBuilder.heartRateNavGraph(
    popMeasurementHeartRateDestination: () -> Unit,
    navigateToStatisticsHeartRateDestination: () -> Unit,
    popStatisticsHeartRateDestination: () -> Unit,
    navigateToMeasurementHeartRateDestination: () -> Unit
) {

    navigation(
        route = HEART_RATE_NAV_GRAPH,
        startDestination = MEASUREMENT_HEART_RATE_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
    ) {
        statisticsHeartRateDestination(
            popStatisticsHeartRateDestination = popStatisticsHeartRateDestination,
            navigateToMeasurementHeartRateDestination = navigateToMeasurementHeartRateDestination
        )

        measurementHeartRateDestination(
            popMeasurementHeartRateDestination = popMeasurementHeartRateDestination,
            navigateToStatisticsHeartRateDestination = navigateToStatisticsHeartRateDestination
        )
    }
}//end heartRateNavGraph