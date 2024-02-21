@file:OptIn(ExperimentalAnimationApi::class)

package com.example.chat.uiElement.screens.chat

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.sharedui.uiElement.containers.navigation.enterTransitionZero
import com.example.sharedui.uiElement.containers.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

//create route name here
private const val CHAT_DESTINATION_ROUTE = "chatDestination"

const val CHAT_DESTINATION_ARGUMENT_ROUTE = "$CHAT_DESTINATION_ROUTE/{${ChatArgs.CHAT_ID_ARGUMENT}}"

//function for push chat destination to top back stack
fun NavHostController.navigateToChatDestination(
    chatId: Int
) {

    //execute navigate to chat destination here
    navigate(
        route = "$CHAT_DESTINATION_ROUTE/$chatId"
    )

}//end navigateToChatDestination

//function for pop chat destination from back stack
fun NavHostController.popChatDestination() {

    //execute pop chat destination from back stack here
    popBackStack(
        route = CHAT_DESTINATION_ARGUMENT_ROUTE,
        inclusive = true
    )

}//end popChatDestination

fun NavGraphBuilder.chatDestination(
    popChatDestination: () -> Unit
) {

    composable(
        route = CHAT_DESTINATION_ARGUMENT_ROUTE,
        arguments = listOf(
            navArgument(
                name = ChatArgs.CHAT_ID_ARGUMENT
            ) {
                type = NavType.IntType
            }//end NavArgumentBuilder
        ),
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() }
    ) {

        ChatScreen(
            popChatDestination = popChatDestination
        )

    }//end composable

}//end chatDestination

internal class ChatArgs(
    savedStateHandle: SavedStateHandle
) {

    //get chat id
    val chatId = checkNotNull(savedStateHandle[CHAT_ID_ARGUMENT])

    companion object {

        //make chat id argument name here
        const val CHAT_ID_ARGUMENT = "chat_id"

    }//end companion object

}//end ChatArgs