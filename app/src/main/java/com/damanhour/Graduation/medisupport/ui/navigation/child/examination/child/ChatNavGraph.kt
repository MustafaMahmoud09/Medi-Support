@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.examination.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.chat.uiElement.screens.chat.chatDestination
import com.example.chat.uiElement.screens.chats.CHATS_DESTINATION_ROUTE
import com.example.chat.uiElement.screens.chats.chatsDestination
import com.example.sharedui.uiElement.components.navigation.enterTransitionZero
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.navigation

//route name here
internal const val CHAT_NAV_GRAPH_ROUTE = "chatNavGraph"

//function for navigate to chat nav graph
internal fun NavHostController.navigateToChatNavGraph() {

    //execute push chat nav graph to back stack here
    navigate(
        route = CHAT_NAV_GRAPH_ROUTE
    )

}//end navigateToChatNavGraph

//function for pop chat nav graph from back stack
internal fun NavHostController.popChatNavGraph() {

    popBackStack(
        route = CHAT_NAV_GRAPH_ROUTE,
        inclusive = true
    )

}//end popChatNavGraph

//function for create chat nav graph and added to destinations in it
internal fun NavGraphBuilder.chatNavGraph(
    popChatNavGraph: () -> Unit,
    navigateToChatDestination: (Int) -> Unit,
    popChatDestination: () -> Unit
) {

    navigation(
        route = CHAT_NAV_GRAPH_ROUTE,
        startDestination = CHATS_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() }
    ) {

        chatsDestination(
            popChatNavGraph = popChatNavGraph,
            navigateToChatDestination = navigateToChatDestination
        )

        chatDestination(
            popChatDestination = popChatDestination
        )

    }//end navigation

}//end chatNavGraph