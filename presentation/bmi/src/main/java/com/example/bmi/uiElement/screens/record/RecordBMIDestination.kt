@file:OptIn(ExperimentalAnimationApi::class)

package com.example.bmi.uiElement.screens.record

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable

const val RECORD_BMI_DESTINATION = "recordBMIDestination"

fun NavGraphBuilder.recordBMIDestination(
    navHostController: NavHostController
) {

    composable(
        route = RECORD_BMI_DESTINATION
    ) {

        RecordBMIScreen(
            navHostController = navHostController
        )
    }
}//end recordBMIDestination