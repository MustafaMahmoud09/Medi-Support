@file:OptIn(ExperimentalAnimationApi::class)

package com.example.reminder.uiElement.screens.add

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.components.navigation.enterTransitionZero
import com.google.accompanist.navigation.animation.composable

//destination name
const val ADD_REMINDER_DESTINATION_ROUTE = "addReminderDestination"


//function for create add reminder destination and create screen in it
fun NavGraphBuilder.addReminderDestination() {

    //create add reminder destination here
    composable(
        route = ADD_REMINDER_DESTINATION_ROUTE,//route name here
        enterTransition = { enterTransitionZero() },//enter transition function here

    ) {

        AddReminderScreen()
    }//end composable

}//end addReminderDestination