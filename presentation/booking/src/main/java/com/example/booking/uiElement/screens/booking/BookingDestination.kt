@file:OptIn(ExperimentalAnimationApi::class)

package com.example.booking.uiElement.screens.booking

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.sharedui.uiElement.containers.navigation.enterTransitionZero
import com.example.sharedui.uiElement.containers.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

//create route name here
private const val BOOKING_DESTINATION_ROUTE = "bookingDestination"

//create route name with argument here
const val BOOKING_DESTINATION_ARGS_ROUTE =
    "$BOOKING_DESTINATION_ROUTE/{${BookingArgs.BOOKING_TYPE}}/{${BookingArgs.DOCTOR_ID}}"


//function for push booking destination to top back stack
fun NavHostController.navigateToBookingDestination(
    bookingType: Boolean,
    doctorId: Int
) {

    //execute push booking destination here
    navigate(
        route = "$BOOKING_DESTINATION_ROUTE/$bookingType/$doctorId"
    )

}//end navigateToBookingNavGraph

//function for pop booking destination from back stack
fun NavHostController.popBookingDestination() {

    //execute pop booking destination here
    popBackStack(
        route = BOOKING_DESTINATION_ARGS_ROUTE,
        inclusive = true
    )

}//end navigateToBookingNavGraph


//function for create booking destination and create design in it
fun NavGraphBuilder.bookingDestination(
    popBookingNavGraph: () -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit
) {

    composable(
        route = BOOKING_DESTINATION_ARGS_ROUTE,
        enterTransition = { enterTransitionZero() },
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() },
        arguments = listOf(
            navArgument(BookingArgs.BOOKING_TYPE) {
                type = NavType.BoolType
            },
            navArgument(BookingArgs.DOCTOR_ID) {
                type = NavType.IntType
            }
        ),
    ) {

        BookingScreen(
            popBookingNavGraph = popBookingNavGraph,
            navigateToBookingDetailsDestination = navigateToBookingDetailsDestination
        )

    }//end composable

}//end bookingDestination


//class created for deal with arguments is passing to this destination during the transition
internal class BookingArgs(
    savedStateHandle: SavedStateHandle
) {

    //get arguments value here
    val bookingType: Boolean = checkNotNull(savedStateHandle[BOOKING_TYPE])
    val doctorId: Int = checkNotNull(savedStateHandle[DOCTOR_ID])

    companion object {

        //create arguments name here
        const val BOOKING_TYPE = "booking_type"
        const val DOCTOR_ID = "doctor_id"

    }//end  companion object

}//end BookingArgs