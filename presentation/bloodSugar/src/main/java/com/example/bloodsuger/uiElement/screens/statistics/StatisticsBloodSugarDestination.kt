@file:OptIn(ExperimentalAnimationApi::class)

package com.example.bloodsuger.uiElement.screens.statistics

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable

const val STATISTICS_BLOOD_SUGAR_DESTINATION_ROUTE = "statisticsBloodSugarDestination"

fun NavHostController.navigateToStatisticsBloodSugarDestination() {

    navigate(
        route = STATISTICS_BLOOD_SUGAR_DESTINATION_ROUTE
    )

}//end navigateToStatisticsBloodSugarDestination

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.statisticsBloodSugarDestination() {

    composable(
        route = STATISTICS_BLOOD_SUGAR_DESTINATION_ROUTE
    ) {
        StatisticsBloodSugarScreen()
    }
}//end statisticsBloodSugarDestination