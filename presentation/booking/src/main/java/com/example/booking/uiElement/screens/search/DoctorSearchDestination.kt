@file:OptIn(ExperimentalAnimationApi::class)

package com.example.booking.uiElement.screens.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

const val DOCTOR_SEARCH_DESTINATION_ROUTE = "doctorSearchDestination"

fun NavGraphBuilder.doctorSearchDestination() {

    composable(
        route = DOCTOR_SEARCH_DESTINATION_ROUTE
    ) {

        DoctorSearchScreen()
    }//end composable

}//end doctorSearchDestination