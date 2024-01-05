@file:OptIn(ExperimentalAnimationApi::class)

package com.example.setting.uiElement.screens.about

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

const val ABOUT_DESTINATION_ROUTE = "aboutDestination"

fun NavGraphBuilder.aboutDestination() {

    composable(
        route = ABOUT_DESTINATION_ROUTE
    ) {

        AboutScreen()
    }//end composable

}//end aboutDestination