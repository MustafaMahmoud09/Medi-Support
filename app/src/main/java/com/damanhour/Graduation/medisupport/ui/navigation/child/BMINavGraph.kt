@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.bmi.uiElement.screens.determination.determinationBMIDestination
import com.example.bmi.uiElement.screens.record.RECORD_BMI_DESTINATION
import com.example.bmi.uiElement.screens.record.recordBMIDestination
import com.example.sharedui.uiElement.components.navigation.enterTransitionZero
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.navigation

//nav graph route here
internal const val BMI_NAV_GRAPH_ROUTE = "bmiNavGraph"

//function for push bmi nav graph to top back stack
internal fun NavHostController.navigateToBmiNavGraph() {
    //navigate to bmi nav graph here
    navigate(
        route = BMI_NAV_GRAPH_ROUTE
    )

}//end navigateToBmiNavGraph

//function for pop bmi nav graph from back stack
internal fun NavHostController.popBmiNavGraph() {
    //pop bmi nav graph from back stack here
    popBackStack(
        route = BMI_NAV_GRAPH_ROUTE,
        inclusive = true
    )

}//end popBmiNavGraph

//function for create bmi nav graph and add destinations to it and define start destination to it
internal fun NavGraphBuilder.bmiNavGraph(
    popBmiNavGraph: () -> Unit,
    navigateToDeterminationBMIDestination: () -> Unit,
    popDeterminationBMIDestination: () -> Unit
) {
    //create bmi nav graph here
    navigation(
        route = BMI_NAV_GRAPH_ROUTE,//route name here
        startDestination = RECORD_BMI_DESTINATION,//define start destination here
        enterTransition = { enterTransitionZero() },//define enter transition function here
        popExitTransition = { exitTransition() },//define exit transition function here
    ) {
        //add destinations to bmi nav graph here
        recordBMIDestination(
            popBmiNavGraph = popBmiNavGraph,
            navigateToDeterminationBMIDestination = navigateToDeterminationBMIDestination
        )

        determinationBMIDestination(
            popDeterminationBMIDestination = popDeterminationBMIDestination,
        )

    }//end navigation

}//end NavGraphBuilder