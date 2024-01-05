@file:OptIn(ExperimentalAnimationApi::class)

package com.example.profile.uiElement.screens.profile

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.components.navigation.BottomDestination
import com.google.accompanist.navigation.animation.composable

val PROFILE_DESTINATION_DATA = BottomDestination(
    route = "profileDestination",
    icon = com.example.sharedui.R.drawable.profile,
    title = com.example.sharedui.R.string.profile
)

fun NavGraphBuilder.profileDestination() {

    composable(
        route = PROFILE_DESTINATION_DATA.route
    ) {

        ProfileScreen()
    }//end composable

}//end profileDestination