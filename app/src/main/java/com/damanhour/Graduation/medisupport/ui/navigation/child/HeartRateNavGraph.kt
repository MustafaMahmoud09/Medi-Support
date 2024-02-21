@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.heartrate.uiElement.screens.measurement.MEASUREMENT_HEART_RATE_DESTINATION_ROUTE
import com.example.heartrate.uiElement.screens.measurement.measurementHeartRateDestination
import com.example.heartrate.uiElement.screens.statistics.statisticsHeartRateDestination
import com.example.sharedui.uiElement.containers.navigation.enterTransitionZero
import com.example.sharedui.uiElement.containers.navigation.exitTransition
import com.google.accompanist.navigation.animation.navigation

internal const val HEART_RATE_NAV_GRAPH = "heartRateNavGraph"

internal fun NavHostController.navigateToHeartRateNavGraph() {

    navigate(
        route = HEART_RATE_NAV_GRAPH
    )

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
        popExitTransition = { exitTransition() }
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