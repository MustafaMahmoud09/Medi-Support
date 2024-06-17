@file:OptIn(ExperimentalAnimationApi::class)

package com.example.room.presentation.uiElement.screens.room

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

const val ONLINE_ROOM_DESTINATION_ROUTE = "onlineRoomDestination"

const val ONLINE_ROOM_DESTINATION_ARGS_ROUTE =
    "$ONLINE_ROOM_DESTINATION_ROUTE/{${OnlineRoomArgs.BOOKING_ID}}"

//function for navigate to online room destination
fun NavHostController.navigateToOnlineRoomDestination(
    poppedDestination: String,
    bookingId: Int
) {

    //execute navigate to online room destination here
    navigate(
        route = "$ONLINE_ROOM_DESTINATION_ROUTE/$bookingId"
    ) {

        popUpTo(
            route = poppedDestination
        ) {

            inclusive = true

        }//end popUpTo

    }//end navigate

}//end navigateToOnlineRoomDestination

//function for navigate to online room destination
fun NavHostController.popOnlineRoomDestination() {

    //execute navigate to online room destination here
   popBackStack(
       route = ONLINE_ROOM_DESTINATION_ARGS_ROUTE,
       inclusive = true
   )

}//end navigateToOnlineRoomDestination

fun NavGraphBuilder.onlineRoomDestination(popOnlineRoomGraph: () -> Unit) {

    composable(
        route = ONLINE_ROOM_DESTINATION_ARGS_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() },
        arguments = listOf(
            navArgument(OnlineRoomArgs.BOOKING_ID) {
                type = NavType.IntType
            }
        ),
    ) {

        OnlineRoomScreen(
            popOnlineRoomGraph = popOnlineRoomGraph
        )

    }//end composable

}//end chatDestination

//class created for deal with arguments is passing to this destination during the transition
internal class OnlineRoomArgs(
    savedStateHandle: SavedStateHandle
) {

    //get arguments value here
    val bookingId: Int = checkNotNull(savedStateHandle[BOOKING_ID])

    companion object {

        //create arguments name here
        const val BOOKING_ID = "booking_id"

    }//end  companion object

}//end BookingArgs