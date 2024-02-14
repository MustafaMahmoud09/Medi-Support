@file:OptIn(ExperimentalAnimationApi::class)

package com.example.chat.uiElement.screens.chats

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

const val CHATS_DESTINATION_ROUTE = "chatsDestination"

fun NavGraphBuilder.chatsDestination() {

    composable(
        route = CHATS_DESTINATION_ROUTE
    ) {

        ChatsScreen()
    }
}//end chatsDestination