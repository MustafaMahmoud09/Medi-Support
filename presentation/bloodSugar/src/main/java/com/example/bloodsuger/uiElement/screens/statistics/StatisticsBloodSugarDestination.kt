@file:OptIn(ExperimentalAnimationApi::class)

package com.example.bloodsuger.uiElement.screens.statistics

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.bloodsuger.uiElement.screens.record.RECORD_BLOOD_SUGAR_DESTINATION_ROUTE
import com.example.sharedui.uiElement.components.navigation.enterTransitionZero
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

const val STATISTICS_BLOOD_SUGAR_DESTINATION_ROUTE = "statisticsBloodSugarDestination"

fun NavHostController.navigateToStatisticsBloodSugarDestination() {

    navigate(
        route = STATISTICS_BLOOD_SUGAR_DESTINATION_ROUTE
    ) {
        //pop determination bmi destination from back stack
        popUpTo(
            route = STATISTICS_BLOOD_SUGAR_DESTINATION_ROUTE
        ) {
            inclusive = true
        }//end popUpTo

        //pop record bmi destination from back stack
        popUpTo(
            route = RECORD_BLOOD_SUGAR_DESTINATION_ROUTE
        ) {
            inclusive = true
        }//end popUpTo
    }

}//end navigateToStatisticsBloodSugarDestination

//function for pop statistics blood pressure Destination from back stack
fun NavHostController.popStatisticsBloodSugarDestination() {

    //pop statistics blood pressure Destination here
    popBackStack(
        route = STATISTICS_BLOOD_SUGAR_DESTINATION_ROUTE,
        inclusive = true
    )

}//end popStatisticsBloodPressureDestination

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.statisticsBloodSugarDestination(
    popStatisticsBloodSugarDestination: () -> Unit,
    navigateToRecordBloodSugarDestination: () -> Unit
) {

    composable(
        route = STATISTICS_BLOOD_SUGAR_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() }
    ) {
        StatisticsBloodSugarScreen(
            popStatisticsBloodSugarDestination = popStatisticsBloodSugarDestination,
            navigateToRecordBloodSugarDestination = navigateToRecordBloodSugarDestination
        )
    }
}//end statisticsBloodSugarDestination