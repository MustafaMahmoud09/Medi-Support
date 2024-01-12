@file:OptIn(ExperimentalAnimationApi::class)

package com.example.reminder.uiElement.screens.records

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable

const val REMINDER_RECORDS_DESTINATION_ROUTE = "reminderRecordsDestination"

fun NavGraphBuilder.reminderRecordsDestination() {

    composable(
        route = REMINDER_RECORDS_DESTINATION_ROUTE
    ) {

        ReminderRecordsScreen()
    }
}//end reminderRecordsDestination