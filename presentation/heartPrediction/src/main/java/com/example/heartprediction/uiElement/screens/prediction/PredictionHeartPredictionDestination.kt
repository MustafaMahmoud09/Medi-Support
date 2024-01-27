@file:OptIn(ExperimentalAnimationApi::class)

package com.example.heartprediction.uiElement.screens.prediction

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

const val PREDICTION_HEART_PREDICTION_DESTINATION_ROUTE = "predictionHeartPredictionDestination"

fun NavGraphBuilder.predictionHeartPredictionDestination() {

    composable(
        route = PREDICTION_HEART_PREDICTION_DESTINATION_ROUTE
    ) {

        PredictionHeartPredictionScreen()
    }
}//end predictionHeartPredictionDestination