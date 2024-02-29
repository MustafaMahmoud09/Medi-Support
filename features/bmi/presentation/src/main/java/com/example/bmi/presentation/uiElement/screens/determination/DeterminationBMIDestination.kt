@file:OptIn(ExperimentalAnimationApi::class)

package com.example.bmi.presentation.uiElement.screens.determination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.bmi.presentation.uiElement.screens.record.RECORD_BMI_DESTINATION
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

//route name
const val DETERMINATION_BMI_DESTINATION_ROUTE = "determinationBMIDestination"

//function for push determination bmi destination to top back stack
fun NavHostController.navigateToDeterminationBMIDestination() {

    //execute navigate method here
    navigate(
        route = DETERMINATION_BMI_DESTINATION_ROUTE
    ) {
        //pop determination bmi destination from back stack
        popUpTo(
            route = DETERMINATION_BMI_DESTINATION_ROUTE
        ) {
            inclusive = true
        }//end popUpTo

        //pop record bmi destination from back stack
        popUpTo(
            route = RECORD_BMI_DESTINATION
        ) {
            inclusive = true
        }//end popUpTo

    }//navigate

}//end navigateToDeterminationBMIDestination

//function for pop determination bmi destination from back stack
fun NavHostController.popDeterminationBMIDestination() {

    //pop determination bmi destination here
    popBackStack(
        route = DETERMINATION_BMI_DESTINATION_ROUTE,
        inclusive = true
    )

}//end popDeterminationBMIDestination

//function for create determination bmi destination and create screen in it
fun NavGraphBuilder.determinationBMIDestination(
    popDeterminationBMIDestination: () -> Unit,
    navigateToRecordBMIDestination: () -> Unit
) {

    //create determination bmi destination here
    composable(
        route = DETERMINATION_BMI_DESTINATION_ROUTE,//define route name here
        enterTransition = { enterTransitionZero() },//define enter transition method here
        popExitTransition = { exitTransition() },//define pop exit transition method here
        exitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
    ) {

        //create determination bmi screen here
        DeterminationBMIScreen(
            popDeterminationBMIDestination = popDeterminationBMIDestination,
            navigateToRecordBMIDestination = navigateToRecordBMIDestination
        )

    }//end composable

}//end determinationBMIDestination