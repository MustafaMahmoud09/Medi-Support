@file:OptIn(ExperimentalAnimationApi::class)

package com.example.bloodpressure.presentation.uiElement.screens.statistics

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

//route name
const val STATISTICS_BLOOD_PRESSURE_DESTINATION_ROUTE = "statisticsBloodPressureDestination"

//function for navigate to statistics blood pressure Destination
fun NavHostController.navigateToStatisticsBloodPressureDestination() {

    //push statistics blood pressure Destination to back stack here
    navigate(
        route = STATISTICS_BLOOD_PRESSURE_DESTINATION_ROUTE
    ) {

        //pop determination bmi destination from back stack
        popUpTo(
            route = STATISTICS_BLOOD_PRESSURE_DESTINATION_ROUTE
        ) {
            inclusive = true
        }//end popUpTo

        //pop record bmi destination from back stack
        popUpTo(
            route = com.example.bloodpressure.presentation.uiElement.screens.record.RECORD_BLOOD_PRESSURE_DESTINATION_ROUTE
        ) {
            inclusive = true
        }//end popUpTo

    }//end navigate

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
    popStatisticsBloodPressureDestination: () -> Unit,
    navigateToRecordBloodPressureDestination: () -> Unit,
    navigateToLoginNavGraphWithPopBottomDestination: () -> Unit
) {
    //create statistics blood pressure destination here
    composable(
        route = STATISTICS_BLOOD_PRESSURE_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
    ) {
        //create statistics blood pressure screen here
        StatisticsBloodPressureScreen(
            popStatisticsBloodPressureDestination = popStatisticsBloodPressureDestination,
            navigateToRecordBloodPressureDestination = navigateToRecordBloodPressureDestination,
            navigateToLoginNavGraphWithPopBottomDestination = navigateToLoginNavGraphWithPopBottomDestination
        )

    }//end composable

}//end statisticsBloodPressureDestination