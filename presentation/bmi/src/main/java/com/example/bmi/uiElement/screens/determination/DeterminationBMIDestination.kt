@file:OptIn(ExperimentalAnimationApi::class)

package com.example.bmi.uiElement.screens.determination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable

const val DETERMINATION_BMI_DESTINATION_ROUTE = "determinationBMIDestination"

fun NavGraphBuilder.determinationBMIDestination(
    navHostController: NavHostController
) {

    composable(
        route = DETERMINATION_BMI_DESTINATION_ROUTE
    ) {

        DeterminationBMIScreen(
            navHostController = navHostController
        )
    }
}//end determinationBMIDestination