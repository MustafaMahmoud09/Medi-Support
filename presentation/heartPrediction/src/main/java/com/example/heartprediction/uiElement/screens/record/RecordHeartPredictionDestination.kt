@file:OptIn(ExperimentalAnimationApi::class)

package com.example.heartprediction.uiElement.screens.record

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.sharedui.uiElement.containers.navigation.enterTransitionZero
import com.example.sharedui.uiElement.containers.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

//route name
const val RECORD_HEART_PREDICTION_DESTINATION_ROUTE = "recordHeartPredictionDestination"

//function for push record heart prediction destination for top back stack
fun NavHostController.navigateToRecordHeartPredictionDestination() {

    //navigate to record heart prediction destination here
    navigate(
        route = RECORD_HEART_PREDICTION_DESTINATION_ROUTE
    )

}//end navigateToRecordHeartPredictionDestination

//function for pop record heart prediction destination from back stack
fun NavHostController.popRecordHeartPredictionDestination() {
    //pop record heart prediction destination here
    popBackStack(
        route = RECORD_HEART_PREDICTION_DESTINATION_ROUTE,
        inclusive = true
    )

}//end popRecordHeartPredictionDestination

//function for create  record heart prediction destination and create record heart prediction in it
fun NavGraphBuilder.recordHeartPredictionDestination(
    popRecordHeartPredictionDestination: () -> Unit,
    navigateToPredictionHeartPredictionDestination: () -> Unit
) {

    //create  record heart prediction destination
    composable(
        route = RECORD_HEART_PREDICTION_DESTINATION_ROUTE,//route name
        enterTransition = { enterTransitionZero() },//enter transition function here
        exitTransition = { exitTransition() },//exist transition function here
        popEnterTransition = { enterTransitionZero() },//pop enter transition function here
        popExitTransition = { exitTransition() }//pop exit transition function here
    ) {
        //create record prediction screen
        RecordHeartPredictionScreen(
            popRecordHeartPredictionDestination = popRecordHeartPredictionDestination,
            navigateToPredictionHeartPredictionDestination = navigateToPredictionHeartPredictionDestination
        )

    }//end composable

}//end recordHeartPredictionDestination