@file:OptIn(ExperimentalAnimationApi::class)

package com.example.setting.uiElement.screens.more

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.setting.uiElement.screens.about.AboutScreen
import com.google.accompanist.navigation.animation.composable

const val MORE_DESTINATION_ROUTE = "moreDestination"

fun NavGraphBuilder.moreDestination() {

    composable(
        route = MORE_DESTINATION_ROUTE
    ) {

        MoreScreen()
    }//end composable

}//end moreDestination