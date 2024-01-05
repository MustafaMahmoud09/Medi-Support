@file:OptIn(ExperimentalAnimationApi::class)

package com.example.booking.uiElement.screens.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.components.navigation.BottomDestination
import com.google.accompanist.navigation.animation.composable


val DOCTOR_SEARCH_DESTINATION_DATA = BottomDestination(
    route = "doctorSearchDestination",
    icon = com.example.sharedui.R.drawable.home,
    title = com.example.sharedui.R.string.home
)

fun NavGraphBuilder.doctorSearchDestination() {

    composable(
        route = DOCTOR_SEARCH_DESTINATION_DATA.route
    ) {

        DoctorSearchScreen()
    }//end composable

}//end doctorSearchDestination