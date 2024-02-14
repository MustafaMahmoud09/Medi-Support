@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.examination.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.booking.uiElement.screens.booking.BOOKING_DESTINATION_ROUTE
import com.example.booking.uiElement.screens.booking.bookingDestination
import com.example.booking.uiElement.screens.payment.paymentDestination
import com.google.accompanist.navigation.animation.navigation

//route name here
internal const val BOOKING_NAV_GRAPH_ROUTE = "bookingNavGraph"

//function for create booking nav graph and added to destinations in it
internal fun NavGraphBuilder.bookingNavGraph() {

    navigation(
        route = BOOKING_NAV_GRAPH_ROUTE,
        startDestination = BOOKING_DESTINATION_ROUTE
    ) {

        bookingDestination()

        paymentDestination()

    }//end navigation

}//end bookingNavGraph