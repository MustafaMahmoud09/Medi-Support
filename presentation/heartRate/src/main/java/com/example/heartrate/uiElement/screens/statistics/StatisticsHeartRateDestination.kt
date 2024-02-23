@file:OptIn(ExperimentalAnimationApi::class)

package com.example.heartrate.uiElement.screens.statistics

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.heartrate.uiElement.screens.measurement.MEASUREMENT_HEART_RATE_DESTINATION_ROUTE
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

const val STATISTICS_HEART_RATE_DESTINATION_ROUTE = "statisticsHeartRateDestination"

fun NavHostController.navigateToStatisticsHeartRateDestination() {

    navigate(
        route = STATISTICS_HEART_RATE_DESTINATION_ROUTE
    ) {

        popUpTo(
            route = STATISTICS_HEART_RATE_DESTINATION_ROUTE
        ) {
            inclusive = true
        }//end popUpTo

        popUpTo(
            route = MEASUREMENT_HEART_RATE_DESTINATION_ROUTE
        ) {
            inclusive = true
        }//end popUpTo

    }//end navigate

}//end navigateToStatisticsHeartRateDestination

//function for pop statistics heart rate Destination from back stack
fun NavHostController.popStatisticsHeartRateDestination() {

    //pop statistics blood pressure Destination here
    popBackStack(
        route = STATISTICS_HEART_RATE_DESTINATION_ROUTE,
        inclusive = true
    )

}//end popStatisticsBloodPressureDestination

fun NavGraphBuilder.statisticsHeartRateDestination(
    popStatisticsHeartRateDestination: () -> Unit,
    navigateToMeasurementHeartRateDestination: () -> Unit
) {

    composable(
        route = STATISTICS_HEART_RATE_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
    ) {

        StatisticsHeartRateScreen(
            popStatisticsHeartRateDestination = popStatisticsHeartRateDestination,
            navigateToMeasurementHeartRateDestination = navigateToMeasurementHeartRateDestination
        )
    }
}//end statisticsHeartRateDestination