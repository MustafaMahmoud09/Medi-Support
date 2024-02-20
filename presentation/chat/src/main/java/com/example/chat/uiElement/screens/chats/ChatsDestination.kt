@file:OptIn(ExperimentalAnimationApi::class)

package com.example.chat.uiElement.screens.chats

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.components.navigation.enterTransitionZero
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

const val CHATS_DESTINATION_ROUTE = "chatsDestination"

fun NavGraphBuilder.chatsDestination(
    popChatNavGraph: () -> Unit,
    navigateToChatDestination: (Int) -> Unit
) {

    composable(
        route = CHATS_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() }
    ) {

        ChatsScreen(
            popChatNavGraph = popChatNavGraph,
            navigateToChatDestination = navigateToChatDestination
        )
    }
}//end chatsDestination