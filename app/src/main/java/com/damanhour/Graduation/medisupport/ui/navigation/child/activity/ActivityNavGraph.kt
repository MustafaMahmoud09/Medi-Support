@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.activity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.navigation.child.activity.child.activity.ACTIVITY_DESTINATION_ROUTE
import com.damanhour.Graduation.medisupport.ui.navigation.child.activity.child.activity.activityDestination
import com.damanhour.Graduation.medisupport.ui.navigation.child.activity.child.history.historyDestination
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.navigation.BottomDestination
import com.example.sharedui.uiElement.components.navigation.enterTransitionMain
import com.example.sharedui.uiElement.components.navigation.exitTransition
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
    navigateToHistoryDestination: () -> Unit
) {

    navigation(
        route = ACTIVITY_NAV_GRAPH_DATA.route,
        startDestination = ACTIVITY_DESTINATION_ROUTE,
        enterTransition = { enterTransitionMain() },
        popExitTransition = { exitTransition() }
    ) {

        activityDestination(
            popActivityNavGraph = popActivityNavGraph,
            navigateToHistoryDestination = navigateToHistoryDestination
        )

        historyDestination(
            popHistoryDestination = popHistoryDestination
        )

    }//end navigation

}//end activityNavGraph