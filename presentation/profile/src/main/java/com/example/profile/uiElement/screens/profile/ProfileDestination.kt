@file:OptIn(ExperimentalAnimationApi::class)

package com.example.profile.uiElement.screens.profile

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.sharedui.uiElement.components.navigation.BottomDestination
import com.example.sharedui.uiElement.components.navigation.enterTransition
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

val PROFILE_DESTINATION_DATA = BottomDestination(
    route = "profileDestination",
    icon = com.example.sharedui.R.drawable.profile,
    title = com.example.sharedui.R.string.profile
)

fun NavHostController.popProfileDestination() {

    popBackStack(
        route = PROFILE_DESTINATION_DATA.route,
        inclusive = true
    )
}//end popProfileDestination

fun NavGraphBuilder.profileDestination(
    popProfileDestination: () -> Unit
) {

    composable(
        route = PROFILE_DESTINATION_DATA.route,
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() }
    ) {

        ProfileScreen(
            popProfileDestination = popProfileDestination
        )
    }//end composable

}//end profileDestination
