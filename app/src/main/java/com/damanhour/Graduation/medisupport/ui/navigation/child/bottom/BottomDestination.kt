@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.bottom

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.AUTH_NAV_GRAPH_ROUTE
import com.example.sharedui.uiElement.components.navigation.enterTransitionMain
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

const val BOTTOM_DESTINATION_ROUTE = "bottomDestination"

internal fun NavHostController.navigateToBottomDestination() {

    navigate(
        route = BOTTOM_DESTINATION_ROUTE
    ) {

        popUpTo(
            route = AUTH_NAV_GRAPH_ROUTE
        ) {
            inclusive = true
        }//end popUpTo

    }//end navigate

}//en navigateToBottomDestination

internal fun NavGraphBuilder.bottomDestination(
    navigateToActivityDestination: () -> Unit
) {

    composable(
        route = BOTTOM_DESTINATION_ROUTE,
        enterTransition = { enterTransitionMain() },
        exitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionMain() }
    ) {

        BottomScreen(
            navigateToActivityDestination = navigateToActivityDestination
        )
    }//end composable

}//end bottomDestination

