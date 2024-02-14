@file:OptIn(ExperimentalAnimationApi::class)

package com.example.booking.uiElement.screens.booking

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable

//route name here
const val BOOKING_DESTINATION_ROUTE = "bookingDestination"

//function for create booking destination and create design in it
fun NavGraphBuilder.bookingDestination() {

    composable(
        route = BOOKING_DESTINATION_ROUTE
    ) {

        BookingScreen()
    }
}//end bookingDestination