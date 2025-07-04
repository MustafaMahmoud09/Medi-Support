@file:OptIn(ExperimentalAnimationApi::class)

package com.example.setting.presentation.uiElement.screens.contactUs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

const val CONTACT_US_DESTINATION_ROUTE = "contactUsDestination"

fun NavHostController.navigateToContactUsDestination() {

    navigate(
        route = CONTACT_US_DESTINATION_ROUTE
    )

}//end navigateToContactUsDestination

fun NavHostController.popContactUsDestination() {

    popBackStack(
        route = CONTACT_US_DESTINATION_ROUTE,
        inclusive = true
    )

}//end popContactUsDestination

fun NavGraphBuilder.contactUsDestination(
    popContactUsDestination: () -> Unit,
    navigateToLoginNavGraphWithPopBottomDestination: () -> Unit
) {

    composable(
        route = CONTACT_US_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
    ) {

        ContactUsScreen(
            popContactUsDestination = popContactUsDestination,
            navigateToLoginNavGraphWithPopBottomDestination = navigateToLoginNavGraphWithPopBottomDestination
        )
    }//end composable

}//end contactUsDestination