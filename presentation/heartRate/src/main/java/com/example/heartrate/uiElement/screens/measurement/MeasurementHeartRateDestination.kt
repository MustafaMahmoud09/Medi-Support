@file:OptIn(ExperimentalAnimationApi::class)

package com.example.heartrate.uiElement.screens.measurement

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable

const val MEASUREMENT_HEART_RATE_DESTINATION_ROUTE = "measurementHeartRateDestination"

fun NavGraphBuilder.measurementHeartRateDestination(
    navHostController: NavHostController
){

    composable(
        route = MEASUREMENT_HEART_RATE_DESTINATION_ROUTE
    ){

        MeasurementHeartRateScreen(
            navHostController = navHostController
        )
    }
}//end measurementHeartRateDestination