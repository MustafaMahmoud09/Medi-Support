@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.bottom.child.child.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.bottom.child.child.home.child.reminderNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.screens.home.DOCTOR_DESTINATION_ROUTE
import com.damanhour.Graduation.medisupport.ui.uiElement.screens.home.doctorsDestination
import com.example.reminder.uiElement.screens.add.ADD_REMINDER_DESTINATION_ROUTE
import com.example.reminder.uiElement.screens.records.REMINDER_RECORDS_DESTINATION_ROUTE
import com.example.sharedui.R
import com.example.sharedui.uiElement.navigation.data.BottomDestination
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.navigation

//create object from bottom destination class have icon and title
//for show in bottom navigation bar and route name
//child list have child destinations to home nav graph to organize bottom nav graph
val HOME_VAV_GRAPH_DATA = BottomDestination(
    route = "homeNavGraph",
    icon = R.drawable.home,
    title = R.string.home,
    childList = listOf(
        DOCTOR_DESTINATION_ROUTE,
        ADD_REMINDER_DESTINATION_ROUTE,
        REMINDER_RECORDS_DESTINATION_ROUTE
    )
)

//function for pop home nav graph from root nav graph
internal fun NavHostController.popHomeNavGraph() {
    //pop home nav graph
    popBackStack(
        route = HOME_VAV_GRAPH_DATA.route,
        inclusive = true
    )

}//end popHomeNavGraph

//function for create home nav graph
//and add destination to it and define start destination to it
internal fun NavGraphBuilder.homeNavGraph(
    navigateToHeartPredictionNavGraph: () -> Unit,
    navigateToAddReminderDestination: () -> Unit,
    popAddReminderDestination: () -> Unit,
    navigateToReminderRecordsDestination: () -> Unit,
    popReminderRecordsDestination: () -> Unit,
    navigateToBmiNavGraph: () -> Unit,
    navigateToBloodPressureNavGraph: () -> Unit,
    navigateToBloodSugarNavGraph: () -> Unit,
    navigateToHeartRateNavGraph: () -> Unit,
    navigateToOnlineBookingNavGraph: (Int) -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit,
    navigateToOfflineBookingDestination: (Int) -> Unit
) {
    //create home nav graph
    navigation(
        route = HOME_VAV_GRAPH_DATA.route,//define home nav graph name
        startDestination = DOCTOR_DESTINATION_ROUTE,//define start destination to home nav graph
        enterTransition = { enterTransitionZero() },//define enter transition method
        exitTransition = { exitTransition() },//define exit transition method
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
    ) {
        //add destinations to home nav graph
        doctorsDestination(
            navigateToHeartPredictionNavGraph = navigateToHeartPredictionNavGraph,
            navigateToAddReminderDestination = navigateToAddReminderDestination,
            navigateToBmiNavGraph = navigateToBmiNavGraph,
            navigateToBloodPressureNavGraph = navigateToBloodPressureNavGraph,
            navigateToBloodSugarNavGraph = navigateToBloodSugarNavGraph,
            navigateToHeartRateNavGraph = navigateToHeartRateNavGraph,
            navigateToOnlineBookingNavGraph = navigateToOnlineBookingNavGraph,
            navigateToBookingDetailsDestination = navigateToBookingDetailsDestination,
            navigateToOfflineBookingDestination = navigateToOfflineBookingDestination
        )

        reminderNavGraph(
            popAddReminderDestination = popAddReminderDestination,
            navigateToReminderRecordsDestination = navigateToReminderRecordsDestination,
            popReminderRecordsDestination = popReminderRecordsDestination
        )

    }//end navigation

}//end homeNavGraph