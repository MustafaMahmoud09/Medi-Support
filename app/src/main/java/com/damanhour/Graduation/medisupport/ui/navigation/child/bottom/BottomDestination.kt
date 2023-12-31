@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.bottom

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.AUTH_NAV_GRAPH_ROUTE
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

internal fun NavGraphBuilder.bottomDestination() {

    composable(
        route = BOTTOM_DESTINATION_ROUTE,
        enterTransition = { enterTransition() }
    ) {

        BottomScreen()
    }//end composable

}//end bottomDestination


private fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition? {

    return when (initialState.destination.route) {

        AUTH_NAV_GRAPH_ROUTE -> {

            fadeIn(
                animationSpec = tween(
                    durationMillis = 300
                )
            )
        }//end case

        else -> null
    }//end when

}//end enterTransition