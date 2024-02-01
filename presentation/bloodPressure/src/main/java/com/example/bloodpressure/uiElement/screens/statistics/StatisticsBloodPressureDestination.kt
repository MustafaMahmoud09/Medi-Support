@file:OptIn(ExperimentalAnimationApi::class)

package com.example.bloodpressure.uiElement.screens.statistics

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.sharedui.uiElement.components.navigation.enterTransitionZero
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

//route name
const val STATISTICS_BLOOD_PRESSURE_DESTINATION_ROUTE = "statisticsBloodPressureDestination"

//function for navigate to statistics blood pressure Destination
fun NavHostController.navigateToStatisticsBloodPressureDestination(){

    //push statistics blood pressure Destination to back stack here
    navigate(
        route = STATISTICS_BLOOD_PRESSURE_DESTINATION_ROUTE
    )

}//end navigateToStatisticsBloodPressureDestination

//function for pop statistics blood pressure Destination from back stack
fun NavHostController.popStatisticsBloodPressureDestination() {

    //pop statistics blood pressure Destination here
    popBackStack(
        route = STATISTICS_BLOOD_PRESSURE_DESTINATION_ROUTE,
        inclusive = true
    )

}//end popStatisticsBloodPressureDestination

//function for create statistics blood pressure destination and create screen in it
fun NavGraphBuilder.statisticsBloodPressureDestination(
    popStatisticsBloodPressureDestination: () -> Unit
) {
    //create statistics blood pressure destination here
    composable(
        route = STATISTICS_BLOOD_PRESSURE_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() }
    ) {
        //create statistics blood pressure screen here
        StatisticsBloodPressureScreen(
            popStatisticsBloodPressureDestination = popStatisticsBloodPressureDestination
        )

    }//end composable

}//end statisticsBloodPressureDestination