@file:OptIn(ExperimentalAnimationApi::class)

package com.example.heartrate.uiElement.screens.statistics

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable

const val STATISTICS_HEART_RATE_DESTINATION_ROUTE = "statisticsHeartRateDestination"

fun NavGraphBuilder.statisticsHeartRateDestination(
    navHostController: NavHostController
) {

    composable(
        route = STATISTICS_HEART_RATE_DESTINATION_ROUTE
    ) {

        StatisticsHeartRateScreen(
            navHostController = navHostController
        )
    }
}//end statisticsHeartRateDestination