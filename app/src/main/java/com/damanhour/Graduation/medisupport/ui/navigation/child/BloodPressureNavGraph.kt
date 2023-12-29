@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.bloodpressure.uiElement.screens.record.recordBloodPressureDestination
import com.example.bloodpressure.uiElement.screens.statistics.STATISTICS_BLOOD_PRESSURE_DESTINATION_ROUTE
import com.example.bloodpressure.uiElement.screens.statistics.statisticsBloodPressureDestination
import com.google.accompanist.navigation.animation.navigation

internal const val BLOOD_PRESSURE_NAV_GRAPH = "bloodPressureNavGraph"

internal fun NavGraphBuilder.bloodPressureNavGraph(
    navHostController: NavHostController
) {

    navigation(
        route = BLOOD_PRESSURE_NAV_GRAPH,
        startDestination = STATISTICS_BLOOD_PRESSURE_DESTINATION_ROUTE
    ) {
        statisticsBloodPressureDestination(
            navHostController = navHostController
        )

        recordBloodPressureDestination(
            navHostController = navHostController
        )
    }
}//end bloodPressureNavGraph