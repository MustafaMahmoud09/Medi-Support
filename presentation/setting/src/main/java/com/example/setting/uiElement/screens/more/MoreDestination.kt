@file:OptIn(ExperimentalAnimationApi::class)

package com.example.setting.uiElement.screens.more

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.components.navigation.enterTransition
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

const val MORE_DESTINATION_ROUTE = "moreDestination"

fun NavGraphBuilder.moreDestination(
    popMoreNavGraph: () -> Unit,
    navigateToAboutDestination: () -> Unit,
    navigateToContactUsDestination: () -> Unit
) {

    composable(
        route = MORE_DESTINATION_ROUTE,
        popEnterTransition = { enterTransition() },
        exitTransition = { exitTransition() }
    ) {

        MoreScreen(
            popMoreNavGraph = popMoreNavGraph,
            navigateToContactUsDestination = navigateToContactUsDestination,
            navigateToAboutDestination = navigateToAboutDestination
        )
    }//end composable

}//end moreDestination