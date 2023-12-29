@file:OptIn(ExperimentalAnimationApi::class)

package com.example.chat.uiElement.screens.chat

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable

const val CHAT_DESTINATION_ROUTE = "chatDestination"

fun NavGraphBuilder.chatDestination(
    navHostController: NavHostController
) {

    composable(
        route = CHAT_DESTINATION_ROUTE
    ) {

        ChatScreen(
            navHostController = navHostController
        )
    }
}//end chatDestination