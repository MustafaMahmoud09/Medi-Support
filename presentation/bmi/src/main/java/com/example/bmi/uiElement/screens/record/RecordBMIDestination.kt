@file:OptIn(ExperimentalAnimationApi::class)

package com.example.bmi.uiElement.screens.record

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

//route name
const val RECORD_BMI_DESTINATION = "recordBMIDestination"

//function for create record bmi destination and create screen in it
fun NavGraphBuilder.recordBMIDestination(
    popBmiNavGraph: () -> Unit,
    navigateToDeterminationBMIDestination: () -> Unit
) {

    //create record bmi destination here
    composable(
        route = RECORD_BMI_DESTINATION,//define route name here
        exitTransition = { exitTransition() },//define exit transition method here
    ) {
        //create record bmi screen here
        RecordBMIScreen(
            popBmiNavGraph = popBmiNavGraph,
            navigateToDeterminationBMIDestination = navigateToDeterminationBMIDestination
        )

    }//end composable

}//end recordBMIDestination