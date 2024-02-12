@file:OptIn(ExperimentalAnimationApi::class)

package com.example.booking.uiElement.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.components.navigation.enterTransitionZero
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

//destination name
const val DOCTOR_SEARCH_DESTINATION_ROUTE = "doctorSearchDestination"

//function for create doctor search destination and add doctor search screen to it
fun NavGraphBuilder.homeDestination(
    navigateToHeartPredictionNavGraph: () -> Unit,
    navigateToAddReminderDestination: () -> Unit,
    navigateToBmiNavGraph: () -> Unit,
    navigateToBloodPressureNavGraph: () -> Unit,
    navigateToBloodSugarNavGraph: () -> Unit,
    navigateToHeartRateNavGraph: () -> Unit
) {
    //create doctor search destination
    composable(
        route = DOCTOR_SEARCH_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() }
    ) {
        //add doctor search screen to doctor search destination
        HomeScreen(
            navigateToHeartPredictionNavGraph = navigateToHeartPredictionNavGraph,
            navigateToAddReminderDestination = navigateToAddReminderDestination,
            navigateToBmiNavGraph = navigateToBmiNavGraph,
            navigateToBloodPressureNavGraph = navigateToBloodPressureNavGraph,
            navigateToBloodSugarNavGraph = navigateToBloodSugarNavGraph,
            navigateToHeartRateNavGraph = navigateToHeartRateNavGraph
        )

    }//end composable

}//end doctorSearchDestination