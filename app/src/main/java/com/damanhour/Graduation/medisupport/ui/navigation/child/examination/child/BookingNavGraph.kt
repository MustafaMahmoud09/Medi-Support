@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.examination.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.booking.uiElement.screens.booking.BOOKING_DESTINATION_ROUTE
import com.example.booking.uiElement.screens.booking.bookingDestination
import com.example.booking.uiElement.screens.payment.paymentDestination
import com.example.sharedui.uiElement.containers.navigation.enterTransitionZero
import com.example.sharedui.uiElement.containers.navigation.exitTransition
import com.google.accompanist.navigation.animation.navigation

//route name here
internal const val BOOKING_NAV_GRAPH_ROUTE = "bookingNavGraph"

//arguments name
private const val BOOKING_TYPE = "booking_type"
private const val DOCTOR_ID = "doctor_id"

//function for push booking nav graph to top back stack
internal fun NavHostController.navigateToBookingNavGraph(
    bookingType: Boolean,
    doctorId: Int
) {

    //execute push booking nav graph here
    navigate(
        route = "$BOOKING_NAV_GRAPH_ROUTE/$bookingType/$doctorId"
    )

}//end navigateToBookingNavGraph

//function for pop booking nav graph from back stack
internal fun NavHostController.popBookingNavGraph() {

    //execute pop booking nav graph here
    popBackStack(
        route = "$BOOKING_NAV_GRAPH_ROUTE/{$BOOKING_TYPE}/{$DOCTOR_ID}",
        inclusive = true
    )

}//end navigateToBookingNavGraph

//function for create booking nav graph and added to destinations in it
internal fun NavGraphBuilder.bookingNavGraph(
    popBookingNavGraph: () -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit
) {

    navigation(
        route = "$BOOKING_NAV_GRAPH_ROUTE/{$BOOKING_TYPE}/{$DOCTOR_ID}",
        startDestination = BOOKING_DESTINATION_ROUTE,
        arguments = listOf(
            navArgument(BOOKING_TYPE) {
                type = NavType.BoolType
            },
            navArgument(DOCTOR_ID) {
                type = NavType.IntType
            }
        ),
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() }
    ) {

        bookingDestination(
            popBookingNavGraph = popBookingNavGraph,
            navigateToBookingDetailsDestination = navigateToBookingDetailsDestination
        )

        paymentDestination()

    }//end navigation

}//end bookingNavGraph