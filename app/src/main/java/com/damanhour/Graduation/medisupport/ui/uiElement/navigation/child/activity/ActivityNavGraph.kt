@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.activity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.uiElement.screens.activity.ACTIVITY_DESTINATION_ROUTE
import com.damanhour.Graduation.medisupport.ui.uiElement.screens.activity.activityDestination
import com.damanhour.Graduation.medisupport.ui.uiElement.screens.history.historyDestination
import com.example.sharedui.R
import com.example.sharedui.uiElement.navigation.data.BottomDestination
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionMain
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.navigation

internal val ACTIVITY_NAV_GRAPH_DATA = BottomDestination(
    route = "activityNavGraph",
    icon = R.drawable.activity,
    title = R.string.activity
)

internal fun NavHostController.navigateToActivityNavGraph() {

    navigate(
        route = ACTIVITY_NAV_GRAPH_DATA.route
    )

}//end navigateToActivityDestination

internal fun NavHostController.popActivityNavGraph() {

    popBackStack(
        route = ACTIVITY_NAV_GRAPH_DATA.route,
        inclusive = true
    )

}//end popActivityNavGraph

@RequiresApi(Build.VERSION_CODES.O)
internal fun NavGraphBuilder.activityNavGraph(
    popActivityNavGraph: () -> Unit,
    popHistoryDestination: () -> Unit,
    navigateToHistoryDestination: () -> Unit,
    navigateToHeartRateNavGraph: () -> Unit,
    navigateToBmiNavGraph: () -> Unit,
    navigateToBloodSugarNavGraph: () -> Unit,
    navigateToBloodPressureNavGraph: () -> Unit
) {

    navigation(
        route = ACTIVITY_NAV_GRAPH_DATA.route,
        startDestination = ACTIVITY_DESTINATION_ROUTE,
        enterTransition = { enterTransitionMain() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
    ) {

        activityDestination(
            popActivityNavGraph = popActivityNavGraph,
            navigateToHistoryDestination = navigateToHistoryDestination,
            navigateToHeartRateNavGraph = navigateToHeartRateNavGraph,
            navigateToBloodPressureNavGraph = navigateToBloodPressureNavGraph,
            navigateToBmiNavGraph = navigateToBmiNavGraph,
            navigateToBloodSugarNavGraph = navigateToBloodSugarNavGraph
        )

        historyDestination(
            popHistoryDestination = popHistoryDestination
        )

    }//end navigation

}//end activityNavGraph