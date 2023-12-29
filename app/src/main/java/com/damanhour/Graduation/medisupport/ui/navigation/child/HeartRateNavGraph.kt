@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.heartrate.uiElement.screens.measurement.measurementHeartRateDestination
import com.example.heartrate.uiElement.screens.statistics.STATISTICS_HEART_RATE_DESTINATION_ROUTE
import com.example.heartrate.uiElement.screens.statistics.statisticsHeartRateDestination
import com.google.accompanist.navigation.animation.navigation

internal const val HEART_RATE_NAV_GRAPH = "heartRateNavGraph"

internal fun NavGraphBuilder.heartRateNavGraph(
    navHostController: NavHostController
) {

    navigation(
        route = HEART_RATE_NAV_GRAPH,
        startDestination = STATISTICS_HEART_RATE_DESTINATION_ROUTE
    ) {
        statisticsHeartRateDestination(
            navHostController = navHostController
        )

        measurementHeartRateDestination(
            navHostController = navHostController
        )
    }
}//end heartRateNavGraph