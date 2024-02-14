@file:OptIn(ExperimentalAnimationApi::class)

package com.example.room.uiElement.screens.room

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable


const val ONLINE_ROOM_DESTINATION_ROUTE = "onlineRoomDestination"

fun NavGraphBuilder.onlineRoomDestination() {

    composable(
        route = ONLINE_ROOM_DESTINATION_ROUTE
    ) {

        OnlineRoomScreen()

    }//end composable

}//end chatDestination