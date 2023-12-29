@file:OptIn(ExperimentalAnimationApi::class)

package com.example.bloodpressure.uiElement.screens.statistics

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable

const val STATISTICS_BLOOD_PRESSURE_DESTINATION_ROUTE = "statisticsBloodPressureDestination"

fun NavGraphBuilder.statisticsBloodPressureDestination(
    navHostController: NavHostController
) {

    composable(
        route = STATISTICS_BLOOD_PRESSURE_DESTINATION_ROUTE
    ) {

        StatisticsBloodPressureScreen(
            navHostController = navHostController
        )
    }
}//end statisticsBloodPressureDestination