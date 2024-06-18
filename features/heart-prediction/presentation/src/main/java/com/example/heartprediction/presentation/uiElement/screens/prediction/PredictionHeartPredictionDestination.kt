@file:OptIn(ExperimentalAnimationApi::class)

package com.example.heartprediction.presentation.uiElement.screens.prediction

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.heartprediction.presentation.uiElement.screens.record.RECORD_HEART_PREDICTION_DESTINATION_ROUTE
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

//route name
private const val PREDICTION_HEART_PREDICTION_DESTINATION_ROUTE =
    "predictionHeartPredictionDestination"

const val PREDICTION_HEART_PREDICTION_ARGS_DESTINATION_ROUTE =
    "${PREDICTION_HEART_PREDICTION_DESTINATION_ROUTE}/{${PredictionHeartDiseaseArgs.RESULT_CLASS}}"

//function for push create prediction heart prediction destination to top back stack
fun NavHostController.navigateToPredictionHeartPredictionDestination(
    resultClass: Int
) {

    //navigate to heart prediction destination here
    navigate(
        route = "$PREDICTION_HEART_PREDICTION_DESTINATION_ROUTE/$resultClass"
    ) {
        //pop record heart destination from back stack
        popUpTo(
            route = RECORD_HEART_PREDICTION_DESTINATION_ROUTE
        ) {
            inclusive = true
        }//end popUpTo

    }//end navigate

}//end navigateToPredictionHeartPredictionDestination

//function for pop prediction heart prediction destination from back stack
fun NavHostController.popPredictionHeartPredictionDestination() {

    //pop prediction heart prediction destination here
    popBackStack(
        route = PREDICTION_HEART_PREDICTION_ARGS_DESTINATION_ROUTE,
        inclusive = true
    )

}//end popPredictionHeartPredictionDestination

//function for create prediction heart prediction destination here and create prediction heart prediction into it
fun NavGraphBuilder.predictionHeartPredictionDestination(
    popPredictionHeartPredictionDestination: () -> Unit
) {

    //create prediction heart prediction destination here
    composable(
        route = PREDICTION_HEART_PREDICTION_ARGS_DESTINATION_ROUTE,//route name here
        enterTransition = { enterTransitionZero() },//enter transition function here
        popExitTransition = { exitTransition() },//pop exist transition function here
        exitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        arguments = listOf(
            navArgument(PredictionHeartDiseaseArgs.RESULT_CLASS) {
                type = NavType.IntType
            }
        ),
    ) {

        //create create prediction heart prediction screen here
        PredictionHeartPredictionScreen(
            popPredictionHeartPredictionDestination = popPredictionHeartPredictionDestination
        )

    }//end composable

}//end predictionHeartPredictionDestination


//class created for deal with arguments is passing to this destination during the transition
internal class PredictionHeartDiseaseArgs(
    savedStateHandle: SavedStateHandle
) {

    //get arguments value here
    val resultClass: Int = checkNotNull(savedStateHandle[RESULT_CLASS])

    companion object {

        //create arguments name here
        const val RESULT_CLASS = "result_class"

    }//end  companion object

}//end BookingArgs