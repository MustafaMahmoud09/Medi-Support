@file:OptIn(ExperimentalAnimationApi::class)

package com.example.bloodsuger.uiElement.screens.record

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable

const val RECORD_BLOOD_SUGAR_DESTINATION_ROUTE = "recordBloodSugarDestination"

fun NavGraphBuilder.recordBloodSugarDestination(
    navHostController: NavHostController
) {

    composable(
        route = RECORD_BLOOD_SUGAR_DESTINATION_ROUTE
    ) {
        RecordBloodSugarScreen(
            navHostController = navHostController
        )
    }
}//end recordBloodSugarDestination