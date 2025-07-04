@file:OptIn(ExperimentalAnimationApi::class)

package com.example.setting.presentation.uiElement.screens.more

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

const val MORE_DESTINATION_ROUTE = "moreDestination"

fun NavGraphBuilder.moreDestination(
    popMoreNavGraph: () -> Unit,
    navigateToAboutDestination: () -> Unit,
    navigateToContactUsDestination: () -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit,
    navigateToLoginNavGraphWithPopBottomDestination: () -> Unit
) {

    composable(
        route = MORE_DESTINATION_ROUTE,
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        enterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() }
    ) {

        MoreScreen(
            popMoreNavGraph = popMoreNavGraph,
            navigateToContactUsDestination = navigateToContactUsDestination,
            navigateToAboutDestination = navigateToAboutDestination,
            navigateToBookingDetailsDestination = navigateToBookingDetailsDestination,
            navigateToLoginNavGraphWithPopBottomDestination = navigateToLoginNavGraphWithPopBottomDestination
        )

    }//end composable

}//end moreDestination