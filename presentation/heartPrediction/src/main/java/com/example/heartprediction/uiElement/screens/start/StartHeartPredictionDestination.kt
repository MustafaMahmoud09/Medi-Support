@file:OptIn(ExperimentalAnimationApi::class)

package com.example.heartprediction.uiElement.screens.start

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

const val START_HEART_PREDICTION_DESTINATION_ROUTE = "startHeartPredictionDestination"

fun NavGraphBuilder.startHeartPredictionDestination(
    popHeartPredictionNavGraph: () -> Unit
) {

    composable(
        route = START_HEART_PREDICTION_DESTINATION_ROUTE
    ) {

        StartHeartPredictionScreen(
            popHeartPredictionNavGraph = popHeartPredictionNavGraph
        )
    }
}//end startHeartPredictionDestination