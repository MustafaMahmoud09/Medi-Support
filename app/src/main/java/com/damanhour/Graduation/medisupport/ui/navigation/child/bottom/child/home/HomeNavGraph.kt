@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.booking.uiElement.screens.search.DOCTOR_SEARCH_DESTINATION_ROUTE
import com.example.booking.uiElement.screens.search.doctorSearchDestination
import com.example.reminder.uiElement.screens.add.ADD_REMINDER_DESTINATION_ROUTE
import com.example.reminder.uiElement.screens.add.addReminderDestination
import com.example.reminder.uiElement.screens.records.REMINDER_RECORDS_DESTINATION_ROUTE
import com.example.reminder.uiElement.screens.records.reminderRecordsDestination
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.navigation.BottomDestination
import com.example.sharedui.uiElement.components.navigation.enterTransitionZero
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.navigation

val HOME_VAV_GRAPH_DATA = BottomDestination(
    route = "homeNavGraph",
    icon = R.drawable.home,
    title = R.string.home,
    childList = listOf(
        DOCTOR_SEARCH_DESTINATION_ROUTE,
        ADD_REMINDER_DESTINATION_ROUTE,
        REMINDER_RECORDS_DESTINATION_ROUTE
    )
)

internal fun NavHostController.popHomeNavGraph() {

    popBackStack(
        route = HOME_VAV_GRAPH_DATA.route,
        inclusive = true
    )

}//end popHomeNavGraph

internal fun NavGraphBuilder.homeNavGraph() {

    navigation(
        route = HOME_VAV_GRAPH_DATA.route,
        startDestination = DOCTOR_SEARCH_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() }
    ) {

        doctorSearchDestination()

        addReminderDestination()

        reminderRecordsDestination()

    }//end navigation

}//end homeNavGraph