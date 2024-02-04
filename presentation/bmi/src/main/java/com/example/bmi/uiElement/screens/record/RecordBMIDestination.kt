@file:OptIn(ExperimentalAnimationApi::class)

package com.example.bmi.uiElement.screens.record

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

//route name
const val RECORD_BMI_DESTINATION = "recordBMIDestination"

fun NavHostController.navigateToRecordBMIDestination() {

    navigate(
        route = RECORD_BMI_DESTINATION
    )

}//navigateToRecordBMIDestination

fun NavHostController.popRecordBMIDestination(){

    popBackStack(
        route = RECORD_BMI_DESTINATION,
        inclusive = true
    )

}//end popRecordBMIDestination

//function for create record bmi destination and create screen in it
fun NavGraphBuilder.recordBMIDestination(
    popRecordBMIDestination: () -> Unit,
    navigateToDeterminationBMIDestination: () -> Unit
) {

    //create record bmi destination here
    composable(
        route = RECORD_BMI_DESTINATION,//define route name here
        exitTransition = { exitTransition() },//define exit transition method here
    ) {
        //create record bmi screen here
        RecordBMIScreen(
            popRecordBMIDestination = popRecordBMIDestination,
            navigateToDeterminationBMIDestination = navigateToDeterminationBMIDestination
        )

    }//end composable

}//end recordBMIDestination