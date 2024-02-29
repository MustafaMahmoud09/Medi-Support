@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.examination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.examination.child.chatNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.examination.child.onlineRoomNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.screens.booking_details.bookingDetailsDestination
import com.example.offlinebooking.presentation.uiElement.screens.booking.offlineBookingDestination
import com.example.onlinebooking.presentation.uiElement.screens.booking.ONLINE_BOOKING_DESTINATION_ARGS_ROUTE
import com.example.onlinebooking.presentation.uiElement.screens.booking.onlineBookingDestination
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.navigation
import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction1

//create route name here
internal const val EXAMINATION_NAV_GRAPH_ROUTE = "examinationNavGraph"

//function for create examination nav graph and added to destinations in it
internal fun NavGraphBuilder.examinationNavGraph(
    popOfflineBookingNavGraph: () -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit,
    popBookingDetailsDestination: KFunction0<Unit>,
    navigateToChatNavGraph: () -> Unit,
    popChatNavGraph: () -> Unit,
    navigateToChatDestination: (Int) -> Unit,
    popChatDestination: () -> Unit,
    navigateToOnlineRoomNavGraph: () -> Unit,
    popOnlineRoomGraph: () -> Unit,
    navigateToOnlineRoomDestination: () -> Unit,
    popOnlineBookingNavGraph: KFunction0<Unit>,
    navigateToBookingDetailsDestinationWithPopOnlineBookingDestination: KFunction1<Int, Unit>
) {

    navigation(
        route = EXAMINATION_NAV_GRAPH_ROUTE,
        startDestination = ONLINE_BOOKING_DESTINATION_ARGS_ROUTE,
        enterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
    ) {

        onlineBookingDestination(
            popBookingNavGraph = popOnlineBookingNavGraph,
            navigateToBookingDetailsDestination = navigateToBookingDetailsDestinationWithPopOnlineBookingDestination
        )

        offlineBookingDestination(
            popBookingNavGraph = popOfflineBookingNavGraph,
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