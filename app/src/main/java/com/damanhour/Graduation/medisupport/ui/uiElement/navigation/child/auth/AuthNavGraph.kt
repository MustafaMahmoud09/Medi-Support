@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.auth

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.auth.login.loginNavGraph
import com.example.auth.presentation.uiElement.screens.register.registerDestination
import com.example.auth.presentation.uiElement.screens.start.START_DESTINATION_ROUTE
import com.example.auth.presentation.uiElement.screens.start.startDestination
import com.example.auth.presentation.uiElement.screens.welcome.welcomeDestination
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.navigation

//nav graph name
internal const val AUTH_NAV_GRAPH_ROUTE = "authNavGraph"

//function for create auth nav graph and add destinations to it and define start destination to it
internal fun NavGraphBuilder.authNavGraph(
    navigateToWelcomeDestination: () -> Unit,
    navigateToLoginNavGraph: () -> Unit,
    navigateToRegisterDestination: () -> Unit,
    popRegisterDestination: () -> Unit,
    navigateToForgotPasswordNavGraph: () -> Unit,
    popForgotPasswordNavGraph: () -> Unit,
    navigateToCodeDestination: () -> Unit,
    navigateToNewPasswordDestination: () -> Unit,
    backToLoginNavGraph: () -> Unit,
    navigateToBottomDestination: () -> Unit
) {
    //create auth nav graph
    navigation(
        route = AUTH_NAV_GRAPH_ROUTE,//define name nav graph
        startDestination = START_DESTINATION_ROUTE,//define start destination to auth nav graph
        enterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
    ) {
        //add destinations to auth nav graph
        startDestination(
            navigateToWelcomeDestination = navigateToWelcomeDestination
        )

        welcomeDestination(
            navigateToLoginNavGraph = navigateToLoginNavGraph
        )

        loginNavGraph(
            navigateToRegisterDestination = navigateToRegisterDestination,
            navigateToForgotPasswordNavGraph = navigateToForgotPasswordNavGraph,
            popForgotPasswordNavGraph = popForgotPasswordNavGraph,
            navigateToCodeDestination = navigateToCodeDestination,
            navigateToNewPasswordDestination = navigateToNewPasswordDestination,
            backToLoginNavGraph = backToLoginNavGraph,
            navigateToBottomDestination = navigateToBottomDestination
        )

        registerDestination(
            popRegisterDestination = popRegisterDestination
        )

    }//end navigation

}//end authNavGraph