@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.activity.ACTIVITY_NAV_GRAPH_DATA
import com.example.bloodpressure.presentation.uiElement.screens.record.RECORD_BLOOD_PRESSURE_DESTINATION_ROUTE
import com.example.bloodpressure.presentation.uiElement.screens.record.recordBloodPressureDestination
import com.example.bloodpressure.presentation.uiElement.screens.statistics.statisticsBloodPressureDestination
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.navigation

//route name
internal const val BLOOD_PRESSURE_NAV_GRAPH = "bloodPressureNavGraph"

//function for navigate to blood pressure nav graph
internal fun NavHostController.navigateToBloodPressureNavGraph() {
    //execute navigate method here
    navigate(
        route = BLOOD_PRESSURE_NAV_GRAPH
    )

}//end navigateToBloodPressureNavGraph

internal fun NavHostController.navigateToBloodPressureNavGraphWithPopActivityNavGraph() {

    navigate(
        route = BLOOD_PRESSURE_NAV_GRAPH
    ) {

        popUpTo(
            route = ACTIVITY_NAV_GRAPH_DATA.route
        ) {

            inclusive = true

        }//end popUpTo

    }//end navigate

}//end navigateToHeartRateNavGraph

//function for pop blood pressure nav graph from back stack
internal fun NavHostController.popBloodPressureNavGraph() {

    //execute pop method here
    popBackStack(
        route = BLOOD_PRESSURE_NAV_GRAPH,
        inclusive = true
    )

}//end popBloodPressureNavGraph

//function for create blood pressure nav graph and create destinations in it
internal fun NavGraphBuilder.bloodPressureNavGraph(
    popRecordBloodPressureDestination: () -> Unit,
    navigateToStatisticsBloodPressureDestination: () -> Unit,
    popStatisticsBloodPressureDestination: () -> Unit,
    navigateToRecordBloodPressureDestination: () -> Unit
) {

    //create blood pressure nav graph here
    navigation(
        route = BLOOD_PRESSURE_NAV_GRAPH,//define route name here
        startDestination = RECORD_BLOOD_PRESSURE_DESTINATION_ROUTE,//define start destination here
        enterTransition = { enterTransitionZero() },//define enter transition method here
        popExitTransition = { exitTransition() },//define pop exit transition method here
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
    ) {
        //create destinations here
        statisticsBloodPressureDestination(
            popStatisticsBloodPressureDestination = popStatisticsBloodPressureDestination,
            navigateToRecordBloodPressureDestination = navigateToRecordBloodPressureDestination
        )

        recordBloodPressureDestination(
            popRecordBloodPressureDestination = popRecordBloodPressureDestination,
            navigateToStatisticsBloodPressureDestination = navigateToStatisticsBloodPressureDestination
        )
    }//end navigation

}//end bloodPressureNavGraph