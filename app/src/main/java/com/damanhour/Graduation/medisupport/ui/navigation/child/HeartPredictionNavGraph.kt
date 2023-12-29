@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.heartprediction.uiElement.screens.prediction.predictionHeartPredictionDestination
import com.example.heartprediction.uiElement.screens.record.recordHeartPredictionDestination
import com.example.heartprediction.uiElement.screens.start.START_HEART_PREDICTION_DESTINATION_ROUTE
import com.example.heartprediction.uiElement.screens.start.startHeartPredictionDestination
import com.google.accompanist.navigation.animation.navigation

internal const val HEART_PREDICTION_NAV_GRAPH_ROUTE = "heartPredictionNavGraph"

internal fun NavGraphBuilder.heartPredictionNavGraph(
    navHostController: NavHostController
) {

    navigation(
        route = HEART_PREDICTION_NAV_GRAPH_ROUTE,
        startDestination = START_HEART_PREDICTION_DESTINATION_ROUTE
    ) {
        startHeartPredictionDestination(
            navHostController = navHostController
        )

        recordHeartPredictionDestination(
            navHostController = navHostController
        )

        predictionHeartPredictionDestination(
            navHostController = navHostController
        )
    }
}//end heartPredictionNavGraph