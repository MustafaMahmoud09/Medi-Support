@file:OptIn(ExperimentalAnimationApi::class)

package com.example.booking.uiElement.screens.details

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable


const val BOOKING_DETAILS_DESTINATION_ROUTE = "bookingDetailsDestination"

fun NavGraphBuilder.bookingDetailsDestination(
    navHostController: NavHostController
) {

    composable(
        route = BOOKING_DETAILS_DESTINATION_ROUTE
    ) {

        BookingDetailsScreen(
            navHostController = navHostController
        )
    }
}//end bookingDetailsDestination