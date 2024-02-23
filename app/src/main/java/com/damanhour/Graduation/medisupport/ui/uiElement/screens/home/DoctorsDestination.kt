@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
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
    navigateToOnlineBookingNavGraph: (Int) -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit,
    navigateToOfflineBookingDestination: (Int) -> Unit
) {
    //create doctor search destination
    composable(
        route = DOCTOR_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
    ) {
        //add doctor search screen to doctor search destination
        DoctorsScreen(
            navigateToHeartPredictionNavGraph = navigateToHeartPredictionNavGraph,
            navigateToAddReminderDestination = navigateToAddReminderDestination,
            navigateToBmiNavGraph = navigateToBmiNavGraph,
            navigateToBloodPressureNavGraph = navigateToBloodPressureNavGraph,
            navigateToBloodSugarNavGraph = navigateToBloodSugarNavGraph,
            navigateToHeartRateNavGraph = navigateToHeartRateNavGraph,
            navigateToOnlineBookingNavGraph = navigateToOnlineBookingNavGraph,
            navigateToBookingDetailsDestination = navigateToBookingDetailsDestination,
            navigateToOfflineBookingDestination = navigateToOfflineBookingDestination
        )

    }//end composable

}//end doctorSearchDestination