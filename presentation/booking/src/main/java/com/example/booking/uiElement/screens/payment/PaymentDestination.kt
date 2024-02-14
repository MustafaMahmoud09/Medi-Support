@file:OptIn(ExperimentalAnimationApi::class)

package com.example.booking.uiElement.screens.payment

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

//route name here
const val PAYMENT_DESTINATION_ROUTE = "paymentDestination"

//function for create payment destination and create design in it
fun NavGraphBuilder.paymentDestination() {

    composable(
        route = PAYMENT_DESTINATION_ROUTE
    ) {

        PaymentScreen()
    }//end composable

}//end bookingDestination