@file:OptIn(ExperimentalAnimationApi::class)

package com.example.heartprediction.uiElement.screens.start

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.containers.navigation.enterTransitionZero
import com.example.sharedui.uiElement.containers.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

//route name
const val START_HEART_PREDICTION_DESTINATION_ROUTE = "startHeartPredictionDestination"

//function for create start heart prediction destination and create screen in it
fun NavGraphBuilder.startHeartPredictionDestination(
    popHeartPredictionNavGraph: () -> Unit,
    navigateToRecordHeartPredictionDestination: () -> Unit
) {
    //create start heart prediction here
    composable(
        route = START_HEART_PREDICTION_DESTINATION_ROUTE,//route name here
        popEnterTransition = { enterTransitionZero() },//pop enter transition function here
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() }
    ) {
        //create start heart prediction screen into destination here
        StartHeartPredictionScreen(
            popHeartPredictionNavGraph = popHeartPredictionNavGraph,
            navigateToRecordHeartPredictionDestination = navigateToRecordHeartPredictionDestination
        )

    }//end composable

}//end startHeartPredictionDestination