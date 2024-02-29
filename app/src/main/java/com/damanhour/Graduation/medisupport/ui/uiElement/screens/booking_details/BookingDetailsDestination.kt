@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.screens.booking_details

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.offlinebooking.presentation.uiElement.screens.booking.OFFLINE_BOOKING_DESTINATION_ARGS_ROUTE
import com.example.onlinebooking.presentation.uiElement.screens.booking.ONLINE_BOOKING_DESTINATION_ARGS_ROUTE
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable
import kotlin.reflect.KFunction0

//create route name here
private const val BOOKING_DETAILS_DESTINATION_ROUTE = "bookingDetailsDestination"

//create route name with arguments
const val BOOKING_DETAILS_DESTINATION_ARGS_ROUTE =
    "$BOOKING_DETAILS_DESTINATION_ROUTE/{${BookingDetailsArgs.PAGE_ARG}}"

fun NavHostController.navigateToBookingDetailsDestinationWithPopOfflineBookingDestination(
    page: Int
) {

    navigate(
        route = "$BOOKING_DETAILS_DESTINATION_ROUTE/$page"
    ) {

        popUpTo(
            route = OFFLINE_BOOKING_DESTINATION_ARGS_ROUTE
        ) {
            inclusive = true
        }//end popUpTo

    }//end navigate

}//end navigateToBookingDetailsDestination

fun NavHostController.navigateToBookingDetailsDestinationWithPopOnlineBookingDestination(
    page: Int
) {

    navigate(
        route = "$BOOKING_DETAILS_DESTINATION_ROUTE/$page"
    ) {

        popUpTo(
            route = ONLINE_BOOKING_DESTINATION_ARGS_ROUTE
        ) {
            inclusive = true
        }//end popUpTo

    }//end navigate

}//end navigateToBookingDetailsDestination

fun NavHostController.popBookingDetailsDestination() {

    popBackStack(
        route = BOOKING_DETAILS_DESTINATION_ARGS_ROUTE,
        inclusive = true
    )

}//end popBookingDetailsDestination

fun NavGraphBuilder.bookingDetailsDestination(
    popBookingDetailsDestination: KFunction0<Unit>,
    navigateToChatNavGraph: () -> Unit,
    navigateToOnlineRoomNavGraph: () -> Unit
) {

    composable(
        route = BOOKING_DETAILS_DESTINATION_ARGS_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        arguments = listOf(
            navArgument(
                name = BookingDetailsArgs.PAGE_ARG
            ) {
                type = NavType.IntType
            }
        ),
    ) {

        BookingDetailsScreen(
            popBookingDetailsDestination = popBookingDetailsDestination,
            navigateToChatNavGraph = navigateToChatNavGraph,
            navigateToOnlineRoomNavGraph = navigateToOnlineRoomNavGraph
        )

    }//end composable

}//end bookingDetailsDestination


//class created for deal with arguments is passing to this destination during the transition
internal class BookingDetailsArgs(
    savedStateHandle: SavedStateHandle
) {

    //get arguments value here
    val page: Int = checkNotNull(savedStateHandle[PAGE_ARG])

    companion object {

        //create arguments name here
        const val PAGE_ARG = "page"

    }//end companion object

}//end BookingDetailsArgs