@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.heartprediction.uiElement.screens.prediction.predictionHeartPredictionDestination
import com.example.heartprediction.uiElement.screens.record.recordHeartPredictionDestination
import com.example.heartprediction.uiElement.screens.start.START_HEART_PREDICTION_DESTINATION_ROUTE
import com.example.heartprediction.uiElement.screens.start.startHeartPredictionDestination
import com.example.sharedui.uiElement.components.navigation.enterTransitionMain
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.navigation

//route name
internal const val HEART_PREDICTION_NAV_GRAPH_ROUTE = "heartPredictionNavGraph"

//function for push heart prediction nav graph to top back stack
internal fun NavHostController.navigateToHeartPredictionNavGraph() {
    //execute push heart prediction nav graph here
    navigate(
        route = HEART_PREDICTION_NAV_GRAPH_ROUTE
    )

}//end navigateToHeartPredictionNavGraph

//function for pop heart prediction nav graph from root nav host
internal fun NavHostController.popHeartPredictionNavGraph() {

    popBackStack(
        route = HEART_PREDICTION_NAV_GRAPH_ROUTE,
        inclusive = true
    )

}//end popHeartPredictionNavGraph

//function for create heart prediction nav graph and add destinations to it and define start destination to it
internal fun NavGraphBuilder.heartPredictionNavGraph(
    popHeartPredictionNavGraph: () -> Unit
) {
    //create heart prediction nav graph here
    navigation(
        route = HEART_PREDICTION_NAV_GRAPH_ROUTE,//define route name here
        startDestination = START_HEART_PREDICTION_DESTINATION_ROUTE,//define start destination here
        enterTransition = { enterTransitionMain() },
        exitTransition = { exitTransition() }
    ) {
        //create destinations here
        startHeartPredictionDestination(
            popHeartPredictionNavGraph = popHeartPredictionNavGraph
        )

        recordHeartPredictionDestination()

        predictionHeartPredictionDestination()

    }//end navigation

}//end heartPredictionNavGraph