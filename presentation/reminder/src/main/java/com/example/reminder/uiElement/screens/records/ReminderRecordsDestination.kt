@file:OptIn(ExperimentalAnimationApi::class)

package com.example.reminder.uiElement.screens.records

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

//destination name
const val REMINDER_RECORDS_DESTINATION_ROUTE = "reminderRecordsDestination"

//function for push reminder records destination to back stack
fun NavHostController.navigateToReminderRecordsDestination() {

    //navigate to reminder records destination here
    navigate(
        route = REMINDER_RECORDS_DESTINATION_ROUTE
    )

}//end navigateToReminderRecordsDestination

//function for pop reminder records destination from back stack
fun NavHostController.popReminderRecordsDestination() {

    //pop reminder records destination here
    popBackStack(
        route = REMINDER_RECORDS_DESTINATION_ROUTE,
        inclusive = true
    )

}//end popReminderRecordsDestination

//function for create reminder records destination and create screen in it
fun NavGraphBuilder.reminderRecordsDestination(
    popReminderRecordsDestination: () -> Unit
) {
    //create reminder records here
    composable(
        route = REMINDER_RECORDS_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() }
    ) {
        //create reminder records screen here
        ReminderRecordsScreen(
            popReminderRecordsDestination = popReminderRecordsDestination
        )

    }//end composable

}//end reminderRecordsDestination