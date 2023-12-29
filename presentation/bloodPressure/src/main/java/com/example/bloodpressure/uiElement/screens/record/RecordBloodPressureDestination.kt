@file:OptIn(ExperimentalAnimationApi::class)

package com.example.bloodpressure.uiElement.screens.record

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable

const val RECORD_BLOOD_PRESSURE_DESTINATION_ROUTE = "recordBloodPressureDestination"

fun NavGraphBuilder.recordBloodPressureDestination(
    navHostController: NavHostController
) {

    composable(
        route = RECORD_BLOOD_PRESSURE_DESTINATION_ROUTE
    ) {

        RecordBloodPressureScreen(
            navHostController = navHostController
        )
    }
}//end recordBloodPressureDestination