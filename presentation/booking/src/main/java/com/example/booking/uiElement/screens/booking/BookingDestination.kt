@file:OptIn(ExperimentalAnimationApi::class)

package com.example.booking.uiElement.screens.booking

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable

const val BOOKING_DESTINATION_ROUTE = "bookingDestination"

fun NavGraphBuilder.bookingDestination(
    navHostController: NavHostController
) {

    composable(
        route = BOOKING_DESTINATION_ROUTE
    ) {

        BookingScreen(
            navHostController = navHostController
        )
    }
}//end bookingDestination