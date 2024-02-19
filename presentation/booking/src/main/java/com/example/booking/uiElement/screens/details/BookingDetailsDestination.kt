@file:OptIn(ExperimentalAnimationApi::class)

package com.example.booking.uiElement.screens.details

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.booking.uiElement.screens.booking.BOOKING_DESTINATION_ROUTE
import com.example.sharedui.uiElement.components.navigation.enterTransitionZero
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable
import kotlin.reflect.KFunction0

const val BOOKING_DETAILS_DESTINATION_ROUTE = "bookingDetailsDestination"

private const val ARGS_ROUTE = "$BOOKING_DETAILS_DESTINATION_ROUTE/{${BookingDetailsArgs.PAGE_ARG}}"

fun NavHostController.navigateToBookingDetailsDestination(
    page: Int
) {

    navigate(
        route = "$BOOKING_DETAILS_DESTINATION_ROUTE/$page"
    ) {

        popUpTo(
            route = BOOKING_DESTINATION_ROUTE
        ) {
            inclusive = true
        }//end popUpTo

    }//end navigate

}//end navigateToBookingDetailsDestination

fun NavHostController.popBookingDetailsDestination() {

    popBackStack(
        route = ARGS_ROUTE,
        inclusive = true
    )

}//end popBookingDetailsDestination

fun NavGraphBuilder.bookingDetailsDestination(
    popBookingDetailsDestination: KFunction0<Unit>,
    navigateToChatNavGraph: () -> Unit
) {

    composable(
        route = ARGS_ROUTE,
        arguments = listOf(
            navArgument(
                name = BookingDetailsArgs.PAGE_ARG
            ) {
                type = NavType.IntType
            }
        ),
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() }
    ) {

        BookingDetailsScreen(
            popBookingDetailsDestination = popBookingDetailsDestination,
            navigateToChatNavGraph = navigateToChatNavGraph
        )

    }//end composable

}//end bookingDetailsDestination


internal class BookingDetailsArgs(
    savedStateHandle: SavedStateHandle
) {
    val page: Int = checkNotNull(savedStateHandle[PAGE_ARG])

    companion object {
        const val PAGE_ARG = "page"
    }//end companion object

}//end BookingDetailsArgs