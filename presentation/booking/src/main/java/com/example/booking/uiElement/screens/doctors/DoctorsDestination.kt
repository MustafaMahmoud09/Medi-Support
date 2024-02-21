@file:OptIn(ExperimentalAnimationApi::class)

package com.example.booking.uiElement.screens.doctors

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.containers.navigation.enterTransitionZero
import com.example.sharedui.uiElement.containers.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

//destination name
const val DOCTOR_DESTINATION_ROUTE = "doctorSearchDestination"

//function for create doctor search destination and add doctor search screen to it
fun NavGraphBuilder.doctorsDestination(
    navigateToHeartPredictionNavGraph: () -> Unit,
    navigateToAddReminderDestination: () -> Unit,
    navigateToBmiNavGraph: () -> Unit,
    navigateToBloodPressureNavGraph: () -> Unit,
    navigateToBloodSugarNavGraph: () -> Unit,
    navigateToHeartRateNavGraph: () -> Unit,
    navigateToBookingNavGraph: (Boolean, Int) -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit
) {
    //create doctor search destination
    composable(
        route = DOCTOR_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() }
    ) {
        //add doctor search screen to doctor search destination
        DoctorsScreen(
            navigateToHeartPredictionNavGraph = navigateToHeartPredictionNavGraph,
            navigateToAddReminderDestination = navigateToAddReminderDestination,
            navigateToBmiNavGraph = navigateToBmiNavGraph,
            navigateToBloodPressureNavGraph = navigateToBloodPressureNavGraph,
            navigateToBloodSugarNavGraph = navigateToBloodSugarNavGraph,
            navigateToHeartRateNavGraph = navigateToHeartRateNavGraph,
            navigateToBookingNavGraph = navigateToBookingNavGraph,
            navigateToBookingDetailsDestination = navigateToBookingDetailsDestination
        )

    }//end composable

}//end doctorSearchDestination