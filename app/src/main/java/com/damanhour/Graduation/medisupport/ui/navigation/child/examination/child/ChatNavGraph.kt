@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.examination.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.chat.uiElement.screens.chat.chatDestination
import com.example.chat.uiElement.screens.chats.CHATS_DESTINATION_ROUTE
import com.example.chat.uiElement.screens.chats.chatsDestination
import com.google.accompanist.navigation.animation.navigation

//route name here
internal const val CHAT_NAV_GRAPH_ROUTE = "chatNavGraph"

//function for create chat nav graph and added to destinations in it
internal fun NavGraphBuilder.chatNavGraph() {

    navigation(
        route = CHAT_NAV_GRAPH_ROUTE,
        startDestination = CHATS_DESTINATION_ROUTE
    ) {

        chatsDestination()

        chatDestination()

    }//end navigation

}//end chatNavGraph