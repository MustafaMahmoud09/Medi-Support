@file:OptIn(ExperimentalAnimationApi::class)

package com.example.room.presentation.uiElement.screens.room

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

const val ONLINE_ROOM_DESTINATION_ROUTE = "onlineRoomDestination"

//function for navigate to online room destination
fun NavHostController.navigateToOnlineRoomDestination(
    poppedDestination: String
) {

    //execute navigate to online room destination here
    navigate(
        route = ONLINE_ROOM_DESTINATION_ROUTE
    ) {

        popUpTo(
            route = poppedDestination
        ) {

            inclusive = true

        }//end popUpTo

    }//end navigate

}//end navigateToOnlineRoomDestination

//function for navigate to online room destination
fun NavHostController.popOnlineRoomDestination(
    poppedDestination: String
) {

    //execute navigate to online room destination here
   popBackStack(
       route = ONLINE_ROOM_DESTINATION_ROUTE,
       inclusive = true
   )

}//end navigateToOnlineRoomDestination

fun NavGraphBuilder.onlineRoomDestination(popOnlineRoomGraph: () -> Unit) {

    composable(
        route = ONLINE_ROOM_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() }
    ) {

        OnlineRoomScreen(
            popOnlineRoomGraph = popOnlineRoomGraph
        )

    }//end composable

}//end chatDestination