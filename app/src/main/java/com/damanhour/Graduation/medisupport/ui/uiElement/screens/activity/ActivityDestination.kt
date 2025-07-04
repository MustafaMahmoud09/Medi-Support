@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.screens.activity

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

const val ACTIVITY_DESTINATION_ROUTE = "activityDestination"


fun NavGraphBuilder.activityDestination(
    popActivityNavGraph: () -> Unit,
    navigateToHistoryDestination: (Int) -> Unit,
    navigateToHeartRateNavGraph: () -> Unit,
    navigateToBloodPressureNavGraph: () -> Unit,
    navigateToBmiNavGraph: () -> Unit,
    navigateToBloodSugarNavGraph: () -> Unit,
    navigateToLoginNavGraphWithPopBottomDestination: () -> Unit
) {

    composable(
        route = ACTIVITY_DESTINATION_ROUTE,
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        enterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() },
    ) {

        ActivityScreen(
            popActivityNavGraph = popActivityNavGraph,
            navigateToHistoryDestination = navigateToHistoryDestination,
            navigateToHeartRateNavGraph = navigateToHeartRateNavGraph,
            navigateToBloodPressureNavGraph = navigateToBloodPressureNavGraph,
            navigateToBloodSugarNavGraph = navigateToBloodSugarNavGraph,
            navigateToBmiNavGraph = navigateToBmiNavGraph,
            navigateToLoginNavGraphWithPopBottomDestination = navigateToLoginNavGraphWithPopBottomDestination
        )

    }//end composable

}//end activityDestination

