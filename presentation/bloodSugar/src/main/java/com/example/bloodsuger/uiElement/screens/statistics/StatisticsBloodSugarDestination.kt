@file:OptIn(ExperimentalAnimationApi::class)

package com.example.bloodsuger.uiElement.screens.statistics

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable

const val STATISTICS_BLOOD_SUGAR_DESTINATION_ROUTE = "statisticsBloodSugarDestination"

fun NavGraphBuilder.statisticsBloodSugarDestination(
    navHostController: NavHostController
) {

    composable(
        route = STATISTICS_BLOOD_SUGAR_DESTINATION_ROUTE
    ) {

    }
}//end statisticsBloodSugarDestination