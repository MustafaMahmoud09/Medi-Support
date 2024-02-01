@file:OptIn(ExperimentalAnimationApi::class)

package com.example.bloodpressure.uiElement.screens.record

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

//route name
const val RECORD_BLOOD_PRESSURE_DESTINATION_ROUTE = "recordBloodPressureDestination"

//function for create record blood pressure destination and create screen in it
fun NavGraphBuilder.recordBloodPressureDestination(
    popBloodPressureNavGraph: () -> Unit,
    navigateToStatisticsBloodPressureDestination: () -> Unit
) {
    //create record blood pressure destination here
    composable(
        route = RECORD_BLOOD_PRESSURE_DESTINATION_ROUTE,
        exitTransition = { exitTransition() },
    ) {

        //create record blood pressure screen here
        RecordBloodPressureScreen(
            popBloodPressureNavGraph = popBloodPressureNavGraph,
            navigateToStatisticsBloodPressureDestination = navigateToStatisticsBloodPressureDestination
        )

    }//end composable

}//end recordBloodPressureDestination