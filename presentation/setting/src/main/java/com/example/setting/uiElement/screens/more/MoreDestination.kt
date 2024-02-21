@file:OptIn(ExperimentalAnimationApi::class)

package com.example.setting.uiElement.screens.more

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.containers.navigation.enterTransitionZero
import com.example.sharedui.uiElement.containers.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

const val MORE_DESTINATION_ROUTE = "moreDestination"

fun NavGraphBuilder.moreDestination(
    popMoreNavGraph: () -> Unit,
    navigateToAboutDestination: () -> Unit,
    navigateToContactUsDestination: () -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit
) {

    composable(
        route = MORE_DESTINATION_ROUTE,
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() }
    ) {

        MoreScreen(
            popMoreNavGraph = popMoreNavGraph,
            navigateToContactUsDestination = navigateToContactUsDestination,
            navigateToAboutDestination = navigateToAboutDestination,
            navigateToBookingDetailsDestination = navigateToBookingDetailsDestination
        )

    }//end composable

}//end moreDestination