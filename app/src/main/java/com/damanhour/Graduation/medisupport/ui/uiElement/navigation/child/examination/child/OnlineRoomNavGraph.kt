@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.examination.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.payment.presentation.uiElement.screens.payment.PAYMENT_DESTINATION_ROUTE
import com.example.payment.presentation.uiElement.screens.payment.paymentDestination
import com.example.room.presentation.uiElement.screens.room.ONLINE_ROOM_DESTINATION_ROUTE
import com.example.room.presentation.uiElement.screens.room.onlineRoomDestination
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.navigation

//route name here
internal const val ONLINE_ROOM_NAV_GRAPH_ROUTE = "onlineRoomNavGraph"

//function for navigate to online room nav graph
internal fun NavHostController.navigateToOnlineRoomNavGraph() {

    //execute push online room nav graph to back stack here
    navigate(
        route = ONLINE_ROOM_NAV_GRAPH_ROUTE
    )

}//end navigateToChatNavGraph

//function for pop online room nav graph from back stack
internal fun NavHostController.popOnlineRoomGraph() {

    popBackStack(
        route = ONLINE_ROOM_NAV_GRAPH_ROUTE,
        inclusive = true
    )

}//end popChatNavGraph

//function for create chat nav graph and added to destinations in it
internal fun NavGraphBuilder.onlineRoomNavGraph(
    popOnlineRoomGraph: () -> Unit,
    navigateToOnlineRoomDestination: () -> Unit
) {

    navigation(
        route = ONLINE_ROOM_NAV_GRAPH_ROUTE,
        startDestination = ONLINE_ROOM_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() }
    ) {

//        paymentDestination(
//            popOnlineRoomGraph = popOnlineRoomGraph,
//            navigateToOnlineRoomDestination = navigateToOnlineRoomDestination
//        )

        onlineRoomDestination()

    }//end navigation

}//end chatNavGraph