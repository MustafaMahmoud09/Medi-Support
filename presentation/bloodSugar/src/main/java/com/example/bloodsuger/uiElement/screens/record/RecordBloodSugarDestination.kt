@file:OptIn(ExperimentalAnimationApi::class)

package com.example.bloodsuger.uiElement.screens.record

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

const val RECORD_BLOOD_SUGAR_DESTINATION_ROUTE = "recordBloodSugarDestination"

fun NavGraphBuilder.recordBloodSugarDestination(
    navigateToStatisticsBloodSugarDestination: () -> Unit,
    popBloodSugarNavGraph: () -> Unit
) {

    composable(
        route = RECORD_BLOOD_SUGAR_DESTINATION_ROUTE
    ) {
        RecordBloodSugarScreen(
           navigateToStatisticsBloodSugarDestination = navigateToStatisticsBloodSugarDestination,
           popBloodSugarNavGraph = popBloodSugarNavGraph
        )
    }
}//end recordBloodSugarDestination