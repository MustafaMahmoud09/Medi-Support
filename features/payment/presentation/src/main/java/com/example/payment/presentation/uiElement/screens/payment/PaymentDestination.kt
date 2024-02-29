@file:OptIn(ExperimentalAnimationApi::class)

package com.example.payment.presentation.uiElement.screens.payment

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

//route name here
const val PAYMENT_DESTINATION_ROUTE = "paymentDestination"

//function for create payment destination and create design in it
fun NavGraphBuilder.paymentDestination(
    popOnlineRoomGraph: () -> Unit,
    navigateToOnlineRoomDestination: () -> Unit
) {

    composable(
        route = PAYMENT_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
    ) {

        PaymentScreen(
            popOnlineRoomGraph = popOnlineRoomGraph,
            navigateToOnlineRoomDestination = navigateToOnlineRoomDestination
        )
    }//end composable

}//end bookingDestination