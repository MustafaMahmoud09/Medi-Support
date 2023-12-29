@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.booking.uiElement.screens.booking.BOOKING_DESTINATION_ROUTE
import com.example.booking.uiElement.screens.booking.bookingDestination
import com.example.booking.uiElement.screens.details.bookingDetailsDestination
import com.example.chat.uiElement.screens.chat.chatDestination
import com.google.accompanist.navigation.animation.navigation

internal const val BOOKING_NAV_GRAPH_ROUTE = "bookingNavGraph"

internal fun NavGraphBuilder.bookingNavGraph(
    navHostController: NavHostController
) {

    navigation(
        route = BOOKING_NAV_GRAPH_ROUTE,
        startDestination = BOOKING_DESTINATION_ROUTE
    ) {
        bookingDestination(
            navHostController = navHostController
        )

        bookingDetailsDestination(
            navHostController = navHostController
        )

        chatDestination(
            navHostController = navHostController
        )
    }
}//end bookingNavGraph