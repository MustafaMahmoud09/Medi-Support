@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.activity.ACTIVITY_NAV_GRAPH_DATA
import com.example.bloodsugar.presentation.uiElement.screens.record.RECORD_BLOOD_SUGAR_DESTINATION_ROUTE
import com.example.bloodsugar.presentation.uiElement.screens.record.recordBloodSugarDestination
import com.example.bloodsugar.presentation.uiElement.screens.statistics.statisticsBloodSugarDestination
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.navigation

//route name
internal const val BLOOD_SUGAR_NAV_GRAPH = "bloodSugarNavGraph"

//function for push blood sugar nav graph to top back stack
internal fun NavHostController.navigateToBloodSugarNavGraph() {
    //navigate to blood sugar nav graph here
    navigate(
        route = BLOOD_SUGAR_NAV_GRAPH
    )

}//end navigateToBloodSugarNavGraph

internal fun NavHostController.navigateToBloodSugarNavGraphWithPopActivityNavGraph() {

    navigate(
        route = BLOOD_SUGAR_NAV_GRAPH
    ) {

        popUpTo(
            route = ACTIVITY_NAV_GRAPH_DATA.route
        ) {

            inclusive = true

        }//end popUpTo

    }//end navigate

}//end navigateToHeartRateNavGraph

internal fun NavHostController.popBloodSugarNavGraph() {

    popBackStack(
        route = BLOOD_SUGAR_NAV_GRAPH,
        inclusive = true
    )

}//end navigateToBloodSugarNavGraph

//function for create blood sugar nav graph and create destinations into it
internal fun NavGraphBuilder.bloodSugarNavGraph(
    navigateToStatisticsBloodSugarDestination: () -> Unit,
    popRecordBloodSugarDestination: () -> Unit,
    popStatisticsBloodSugarDestination: () -> Unit,
    navigateToRecordBloodSugarDestination: () -> Unit,
    navigateToLoginNavGraphWithPopBottomDestination: () -> Unit
) {
    //create blood sugar nav graph
    navigation(
        route = BLOOD_SUGAR_NAV_GRAPH,//define route name here
        startDestination = RECORD_BLOOD_SUGAR_DESTINATION_ROUTE,//define start destination here
        enterTransition = { enterTransitionZero() },//define enter transition method here
        popExitTransition = { exitTransition() },//define pop exit transition here
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
    ) {
        //create destinations into nav graph here
        statisticsBloodSugarDestination(
            popStatisticsBloodSugarDestination = popStatisticsBloodSugarDestination,
            navigateToRecordBloodSugarDestination = navigateToRecordBloodSugarDestination
        )

        recordBloodSugarDestination(
            navigateToStatisticsBloodSugarDestination = navigateToStatisticsBloodSugarDestination,
            popRecordBloodSugarDestination = popRecordBloodSugarDestination,
            navigateToLoginNavGraphWithPopBottomDestination = navigateToLoginNavGraphWithPopBottomDestination
        )

    }//end navigation

}//end bloodSugarNavGraph