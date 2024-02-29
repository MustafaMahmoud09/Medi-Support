@file:OptIn(ExperimentalAnimationApi::class)

package com.example.heartrate.presentation.uiElement.screens.measurement

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

const val MEASUREMENT_HEART_RATE_DESTINATION_ROUTE = "measurementHeartRateDestination"

fun NavHostController.navigateToMeasurementHeartRateDestination() {

    navigate(
        route = MEASUREMENT_HEART_RATE_DESTINATION_ROUTE
    )

}//navigateToRecordBMIDestination

fun NavHostController.popMeasurementHeartRateDestination() {

    popBackStack(
        route = MEASUREMENT_HEART_RATE_DESTINATION_ROUTE,
        inclusive = true
    )

}//end popMeasurementHeartRateDestination

fun NavGraphBuilder.measurementHeartRateDestination(
    popMeasurementHeartRateDestination: () -> Unit,
    navigateToStatisticsHeartRateDestination: () -> Unit
) {

    composable(
        route = MEASUREMENT_HEART_RATE_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() }
    ) {

        MeasurementHeartRateScreen(
            popMeasurementHeartRateDestination = popMeasurementHeartRateDestination,
            navigateToStatisticsHeartRateDestination = navigateToStatisticsHeartRateDestination
        )
    }
}//end measurementHeartRateDestination