@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.bottom.child.child.home.child

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.reminder.presentation.uiElement.screens.add.ADD_REMINDER_DESTINATION_ROUTE
import com.example.reminder.presentation.uiElement.screens.add.addReminderDestination
import com.example.reminder.presentation.uiElement.screens.records.reminderRecordsDestination
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.navigation

//destination name
const val REMINDER_NAV_GRAPH_ROUTE = "reminderNavGraph"

//function for push add reminder destination to back stack
fun NavHostController.navigateToAddReminderNavGraph() {

    //navigate to add reminder destination here
    navigate(
        route = REMINDER_NAV_GRAPH_ROUTE
    )

}//end navigateToAddReminderDestination

//function for pop add reminder destination from back stack
fun NavHostController.popAddReminderNavGraph() {

    //pop add reminder destination here
    popBackStack(
        route = REMINDER_NAV_GRAPH_ROUTE,
        inclusive = true
    )

}//end popAddReminderDestination


//function for create reminder nav graph
//and add destination to it and define start destination to it
@RequiresApi(Build.VERSION_CODES.O)
internal fun NavGraphBuilder.reminderNavGraph(
    popAddReminderDestination: () -> Unit,
    navigateToReminderRecordsDestination: () -> Unit,
    popReminderRecordsDestination: () -> Unit
) {

    //create home nav graph
    navigation(
        route = REMINDER_NAV_GRAPH_ROUTE,//define home nav graph name
        startDestination = ADD_REMINDER_DESTINATION_ROUTE,//define start destination to home nav graph
        enterTransition = { enterTransitionZero() },//define enter transition method
        exitTransition = { exitTransition() },//define exit transition method
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
    ){

        addReminderDestination(
            popAddReminderDestination = popAddReminderDestination,
            navigateToReminderRecordsDestination = navigateToReminderRecordsDestination
        )

        reminderRecordsDestination(
            popReminderRecordsDestination = popReminderRecordsDestination
        )

    }//end navigation

}//end reminderNavGraph