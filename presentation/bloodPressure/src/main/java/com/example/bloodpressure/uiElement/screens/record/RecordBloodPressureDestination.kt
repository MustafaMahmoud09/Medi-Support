@file:OptIn(ExperimentalAnimationApi::class)

package com.example.bloodpressure.uiElement.screens.record

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.sharedui.uiElement.containers.navigation.enterTransitionZero
import com.example.sharedui.uiElement.containers.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

//route name
const val RECORD_BLOOD_PRESSURE_DESTINATION_ROUTE = "recordBloodPressureDestination"

fun NavHostController.navigateToRecordBloodPressureDestination() {

    navigate(
        route = RECORD_BLOOD_PRESSURE_DESTINATION_ROUTE
    )

}//navigateToRecordBMIDestination

fun NavHostController.popRecordBloodPressureDestination(){

    popBackStack(
        route = RECORD_BLOOD_PRESSURE_DESTINATION_ROUTE,
        inclusive = true
    )

}//end popRecordBMIDestination

//function for create record blood pressure destination and create screen in it
fun NavGraphBuilder.recordBloodPressureDestination(
    popRecordBloodPressureDestination: () -> Unit,
    navigateToStatisticsBloodPressureDestination: () -> Unit
) {
    //create record blood pressure destination here
    composable(
        route = RECORD_BLOOD_PRESSURE_DESTINATION_ROUTE,
        exitTransition = { exitTransition() },
        enterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
    ) {

        //create record blood pressure screen here
        RecordBloodPressureScreen(
            popRecordBloodPressureDestination = popRecordBloodPressureDestination,
            navigateToStatisticsBloodPressureDestination = navigateToStatisticsBloodPressureDestination
        )

    }//end composable

}//end recordBloodPressureDestination