@file:OptIn(ExperimentalAnimationApi::class)

package com.example.setting.presentation.uiElement.screens.profile

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.sharedui.R
import com.example.sharedui.uiElement.navigation.data.BottomDestination
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

val PROFILE_DESTINATION_DATA = BottomDestination(
    route = "profileDestination",
    icon = R.drawable.profile,
    title = R.string.profile
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
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() }
    ) {

        ProfileScreen(
            popProfileDestination = popProfileDestination
        )
    }//end composable

}//end profileDestination
