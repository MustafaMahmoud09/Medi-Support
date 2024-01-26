@file:OptIn(ExperimentalAnimationApi::class)

package com.example.booking.uiElement.screens.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.components.navigation.enterTransitionZero
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

//destination name
const val DOCTOR_SEARCH_DESTINATION_ROUTE = "doctorSearchDestination"

//function for create doctor search destination and add doctor search screen to it
fun NavGraphBuilder.doctorSearchDestination() {
    //create doctor search destination
    composable(
        route = DOCTOR_SEARCH_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() }
    ) {
        //add doctor search screen to doctor search destination
        DoctorSearchScreen()

    }//end composable

}//end doctorSearchDestination