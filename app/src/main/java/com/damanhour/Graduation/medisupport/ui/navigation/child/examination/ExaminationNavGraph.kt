@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.examination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.damanhour.Graduation.medisupport.ui.navigation.child.examination.child.chatNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.examination.child.onlineRoomNavGraph
import com.example.booking.uiElement.screens.booking.BOOKING_DESTINATION_ARGS_ROUTE
import com.example.booking.uiElement.screens.booking.bookingDestination
import com.example.booking.uiElement.screens.details.bookingDetailsDestination
import com.example.sharedui.uiElement.containers.navigation.enterTransitionZero
import com.example.sharedui.uiElement.containers.navigation.exitTransition
import com.google.accompanist.navigation.animation.navigation
import kotlin.reflect.KFunction0

//create route name here
internal const val EXAMINATION_NAV_GRAPH_ROUTE = "examinationNavGraph"

//function for create examination nav graph and added to destinations in it
internal fun NavGraphBuilder.examinationNavGraph(
    popBookingNavGraph: () -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit,
    popBookingDetailsDestination: KFunction0<Unit>,
    navigateToChatNavGraph: () -> Unit,
    popChatNavGraph: () -> Unit,
    navigateToChatDestination: (Int) -> Unit,
    popChatDestination: () -> Unit,
    navigateToOnlineRoomNavGraph: () -> Unit,
    popOnlineRoomGraph: () -> Unit,
    navigateToOnlineRoomDestination: () -> Unit
) {

    navigation(
        route = EXAMINATION_NAV_GRAPH_ROUTE,
        startDestination = BOOKING_DESTINATION_ARGS_ROUTE,
        enterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
    ) {

        bookingDestination(
            popBookingNavGraph = popBookingNavGraph,
            navigateToBookingDetailsDestination = navigateToBookingDetailsDestination
        )

        bookingDetailsDestination(
            popBookingDetailsDestination = popBookingDetailsDestination,
            navigateToChatNavGraph = navigateToChatNavGraph,
            navigateToOnlineRoomNavGraph = navigateToOnlineRoomNavGraph
        )

        onlineRoomNavGraph(
            popOnlineRoomGraph = popOnlineRoomGraph,
            navigateToOnlineRoomDestination = navigateToOnlineRoomDestination
        )

        chatNavGraph(
            popChatNavGraph = popChatNavGraph,
            navigateToChatDestination = navigateToChatDestination,
            popChatDestination = popChatDestination
        )

    }//end navigation

}//end bookingNavGraph