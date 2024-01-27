@file:OptIn(ExperimentalAnimationApi::class)

package com.example.heartprediction.uiElement.screens.record

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable


const val RECORD_HEART_PREDICTION_DESTINATION_ROUTE = "recordHeartPredictionDestination"

fun NavGraphBuilder.recordHeartPredictionDestination() {

    composable(
        route = RECORD_HEART_PREDICTION_DESTINATION_ROUTE
    ) {

        RecordHeartPredictionScreen()
    }
}//end recordHeartPredictionDestination