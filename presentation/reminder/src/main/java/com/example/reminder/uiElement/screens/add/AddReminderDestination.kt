@file:OptIn(ExperimentalAnimationApi::class)

package com.example.reminder.uiElement.screens.add

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable

const val ADD_REMINDER_DESTINATION_ROUTE = "addReminderDestination"

fun NavGraphBuilder.addReminderDestination(
    navHostController: NavHostController
) {

    composable(
        route = ADD_REMINDER_DESTINATION_ROUTE
    ) {

        AddReminderScreen(
            navHostController = navHostController
        )
    }
}//end addReminderDestination