@file:OptIn(ExperimentalAnimationApi::class)

package com.example.bloodsugar.presentation.uiElement.screens.record

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.bloodsugar.presentation.uiElement.screens.statistics.STATISTICS_BLOOD_SUGAR_DESTINATION_ROUTE
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

const val RECORD_BLOOD_SUGAR_DESTINATION_ROUTE = "recordBloodSugarDestination"

fun NavHostController.navigateToRecordBloodSugarDestination() {

    navigate(
        route = RECORD_BLOOD_SUGAR_DESTINATION_ROUTE
    ){
        popUpTo(
            route = STATISTICS_BLOOD_SUGAR_DESTINATION_ROUTE
        ){
            inclusive = true
        }
    }

}//navigateToRecordBMIDestination

fun NavHostController.popRecordBloodSugarDestination() {

    popBackStack(
        route = RECORD_BLOOD_SUGAR_DESTINATION_ROUTE,
        inclusive = true
    )

}//end popRecordBMIDestination

fun NavGraphBuilder.recordBloodSugarDestination(
    navigateToStatisticsBloodSugarDestination: () -> Unit,
    popRecordBloodSugarDestination: () -> Unit,
    navigateToLoginNavGraphWithPopBottomDestination: () -> Unit
) {

    composable(
        route = RECORD_BLOOD_SUGAR_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
    ) {
        RecordBloodSugarScreen(
            navigateToStatisticsBloodSugarDestination = navigateToStatisticsBloodSugarDestination,
            popRecordBloodSugarDestination = popRecordBloodSugarDestination,
            navigateToLoginNavGraphWithPopBottomDestination = navigateToLoginNavGraphWithPopBottomDestination
        )
    }
}//end recordBloodSugarDestination