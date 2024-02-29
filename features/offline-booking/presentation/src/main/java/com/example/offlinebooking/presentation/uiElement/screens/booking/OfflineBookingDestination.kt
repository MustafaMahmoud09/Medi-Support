@file:OptIn(ExperimentalAnimationApi::class)

package com.example.offlinebooking.presentation.uiElement.screens.booking

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

//create route name here
private const val OFFLINE_BOOKING_DESTINATION_ROUTE = "offlineBookingDestination"

//create route name with argument here
const val OFFLINE_BOOKING_DESTINATION_ARGS_ROUTE =
    "$OFFLINE_BOOKING_DESTINATION_ROUTE/{${OfflineBookingArgs.DOCTOR_ID}}"


//function for push booking destination to top back stack
fun NavHostController.navigateToOfflineBookingDestination(
    doctorId: Int
) {

    //execute push booking destination here
    navigate(
        route = "$OFFLINE_BOOKING_DESTINATION_ROUTE/$doctorId"
    )

}//end navigateToBookingNavGraph

//function for pop booking destination from back stack
fun NavHostController.popOfflineBookingDestination() {

    //execute pop booking destination here
    popBackStack(
        route = OFFLINE_BOOKING_DESTINATION_ARGS_ROUTE,
        inclusive = true
    )

}//end navigateToBookingNavGraph


//function for create booking destination and create design in it
fun NavGraphBuilder.offlineBookingDestination(
    popBookingNavGraph: () -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit
) {

    composable(
        route = OFFLINE_BOOKING_DESTINATION_ARGS_ROUTE,
        enterTransition = { enterTransitionZero() },
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() },
        arguments = listOf(
            navArgument(OfflineBookingArgs.DOCTOR_ID) {
                type = NavType.IntType
            }
        ),
    ) {

        OfflineBookingScreen(
            popBookingNavGraph = popBookingNavGraph,
            navigateToBookingDetailsDestination = navigateToBookingDetailsDestination
        )

    }//end composable

}//end bookingDestination


//class created for deal with arguments is passing to this destination during the transition
internal class OfflineBookingArgs(
    savedStateHandle: SavedStateHandle
) {

    //get arguments value here
    val doctorId: Int = checkNotNull(savedStateHandle[DOCTOR_ID])

    companion object {

        //create arguments name here
        const val DOCTOR_ID = "doctor_id"

    }//end  companion object

}//end BookingArgs