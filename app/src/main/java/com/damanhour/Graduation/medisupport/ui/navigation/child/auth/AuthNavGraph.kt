@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.auth

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.loginNavGraph
import com.example.auth.uiElement.screens.register.registerDestination
import com.example.auth.uiElement.screens.start.START_DESTINATION_ROUTE
import com.example.auth.uiElement.screens.start.startDestination
import com.example.auth.uiElement.screens.welcome.welcomeDestination
import com.google.accompanist.navigation.animation.navigation

internal const val AUTH_NAV_GRAPH_ROUTE = "authNavGraph"

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

    navigation(
        route = AUTH_NAV_GRAPH_ROUTE,
        startDestination = START_DESTINATION_ROUTE
    ) {

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