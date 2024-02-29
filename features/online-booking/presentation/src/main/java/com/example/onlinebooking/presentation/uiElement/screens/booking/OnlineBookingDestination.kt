@file:OptIn(ExperimentalAnimationApi::class)

package com.example.onlinebooking.presentation.uiElement.screens.booking

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
private const val ONLINE_BOOKING_DESTINATION_ROUTE = "onlineBookingDestination"

//create route name with argument here
const val ONLINE_BOOKING_DESTINATION_ARGS_ROUTE =
    "$ONLINE_BOOKING_DESTINATION_ROUTE/{${OnlineBookingArgs.DOCTOR_ID}}"


//function for push booking destination to top back stack
fun NavHostController.navigateToOnlineBookingDestination(
    doctorId: Int
) {

    //execute push booking destination here
    navigate(
        route = "$ONLINE_BOOKING_DESTINATION_ROUTE/$doctorId"
    )

}//end navigateToBookingNavGraph

//function for pop booking destination from back stack
fun NavHostController.popOnlineBookingDestination() {

    //execute pop booking destination here
    popBackStack(
        route = ONLINE_BOOKING_DESTINATION_ARGS_ROUTE,
        inclusive = true
    )

}//end navigateToBookingNavGraph


//function for create booking destination and create design in it
fun NavGraphBuilder.onlineBookingDestination(
    popBookingNavGraph: () -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit
) {

    composable(
        route = ONLINE_BOOKING_DESTINATION_ARGS_ROUTE,
        enterTransition = { enterTransitionZero() },
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() },
        arguments = listOf(
            navArgument(OnlineBookingArgs.DOCTOR_ID) {
                type = NavType.IntType
            }
        ),
    ) {

        OnlineBookingScreen(
            popBookingNavGraph = popBookingNavGraph,
            navigateToBookingDetailsDestination = navigateToBookingDetailsDestination
        )

    }//end composable

}//end bookingDestination


//class created for deal with arguments is passing to this destination during the transition
internal class OnlineBookingArgs(
    savedStateHandle: SavedStateHandle
) {

    //get arguments value here
    val doctorId: Int = checkNotNull(savedStateHandle[DOCTOR_ID])

    companion object {

        //create arguments name here
        const val DOCTOR_ID = "doctor_id"

    }//end  companion object

}//end BookingArgs