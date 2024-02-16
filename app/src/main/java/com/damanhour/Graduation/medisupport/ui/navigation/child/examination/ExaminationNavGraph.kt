@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.examination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.damanhour.Graduation.medisupport.ui.navigation.child.examination.child.BOOKING_NAV_GRAPH_ROUTE
import com.damanhour.Graduation.medisupport.ui.navigation.child.examination.child.bookingNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.examination.child.chatNavGraph
import com.example.booking.uiElement.screens.details.bookingDetailsDestination
import com.example.room.uiElement.screens.room.onlineRoomDestination
import com.google.accompanist.navigation.animation.navigation

//route name here
internal const val EXAMINATION_NAV_GRAPH_ROUTE = "examinationNavGraph"

//function for create examination nav graph and added to destinations in it
internal fun NavGraphBuilder.examinationNavGraph(
    popBookingNavGraph: () -> Unit
) {

    navigation(
        route = EXAMINATION_NAV_GRAPH_ROUTE,
        startDestination = BOOKING_NAV_GRAPH_ROUTE
    ) {

        bookingNavGraph(
            popBookingNavGraph = popBookingNavGraph
        )

        bookingDetailsDestination()

        onlineRoomDestination()

        chatNavGraph()

    }//end navigation

}//end bookingNavGraph