@file:OptIn(ExperimentalAnimationApi::class)

package com.example.booking.uiElement.screens.booking

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.components.navigation.enterTransitionZero
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

//route name here
const val BOOKING_DESTINATION_ROUTE = "bookingDestination"

//function for create booking destination and create design in it
fun NavGraphBuilder.bookingDestination(
    popBookingNavGraph: () -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit
) {

    composable(
        route = BOOKING_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() }
    ) {

        BookingScreen(
            popBookingNavGraph = popBookingNavGraph,
            navigateToBookingDetailsDestination = navigateToBookingDetailsDestination
        )

    }//end composable

}//end bookingDestination