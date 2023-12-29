@file:OptIn(ExperimentalAnimationApi::class)

package com.example.heartprediction.uiElement.screens.start

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable

const val START_HEART_PREDICTION_DESTINATION_ROUTE = "startHeartPredictionDestination"

fun NavGraphBuilder.startHeartPredictionDestination(
    navHostController: NavHostController
) {

    composable(
        route = START_HEART_PREDICTION_DESTINATION_ROUTE
    ) {

        StartHeartPredictionScreen(
            navHostController = navHostController
        )
    }
}//end startHeartPredictionDestination