@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.screens.history

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.damanhour.Graduation.medisupport.ui.uiElement.screens.booking_details.BookingDetailsArgs
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

private const val HISTORY_DESTINATION_ROUTE = "historyDestination"

//create route name with arguments
const val HISTORY_DESTINATION_ARGS_ROUTE =
    "$HISTORY_DESTINATION_ROUTE/{${BookingDetailsArgs.PAGE_ARG}}"

fun NavHostController.navigateToHistoryDestination(
    page: Int
) {

    navigate(
        route = "$HISTORY_DESTINATION_ROUTE/$page"
    )

}//end navigateToHistoryDestination

fun NavHostController.popHistoryDestination() {

    popBackStack(
        route = HISTORY_DESTINATION_ARGS_ROUTE,
        inclusive = true
    )

}//end popHistoryDestination

fun NavGraphBuilder.historyDestination(
    popHistoryDestination: () -> Unit
) {

    composable(
        route = HISTORY_DESTINATION_ARGS_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        arguments = listOf(
            navArgument(
                name = BookingDetailsArgs.PAGE_ARG
            ) {
                type = NavType.IntType
            }
        ),
    ) {

        HistoryScreen(
            popHistoryDestination = popHistoryDestination
        )
    }//end composable

}//end historyDestination


//class created for deal with arguments is passing to this destination during the transition
internal class HistoryArgs(
    savedStateHandle: SavedStateHandle
) {

    //get arguments value here
    val page: Int = checkNotNull(savedStateHandle[PAGE_ARG])

    companion object {

        //create arguments name here
        const val PAGE_ARG = "page"

    }//end companion object

}//end BookingDetailsArgs
