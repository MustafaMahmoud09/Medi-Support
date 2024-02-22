@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.forgotten.FORGOT_PASSWORD_NAV_GRAPH
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.forgotten.forgotPasswordNavGraph
import com.example.auth.uiElement.screens.login.LOGIN_DESTINATION_ROUTE
import com.example.auth.uiElement.screens.login.loginDestination
import com.example.auth.uiElement.screens.welcome.WELCOME_DESTINATION_ROUTE
import com.example.sharedui.uiElement.containers.navigation.enterTransitionMain
import com.example.sharedui.uiElement.containers.navigation.enterTransitionZero
import com.example.sharedui.uiElement.containers.navigation.exitTransition
import com.google.accompanist.navigation.animation.navigation

//nav graph name
internal const val LOGIN_NAV_GRAPH_ROUTE = "loginNavGraph"

//function for add login nav graph to top root nav graph and pop welcome destination from back stack
internal fun NavHostController.navigateToLoginNavGraphWithPopWelcomeDestination() {
    //navigate to login nav graph
    navigate(
        route = LOGIN_NAV_GRAPH_ROUTE
    ) {
        //pop welcome destination from back stack
        popUpTo(
            route = WELCOME_DESTINATION_ROUTE
        ) {
            inclusive = true
        }//end popUpTo

    }//end navigate

}//end navigateToLoginNavGraphWithPopWelcomeDestination

//function for add login nav graph to top root nav graph and pop forgot password nav graph from back stack
internal fun NavHostController.navigateToLoginNavGraphWithPopForgotPasswordNavGraph() {
    //navigate to login nav graph
    navigate(
        route = LOGIN_NAV_GRAPH_ROUTE
    ) {
        //pop forgot password nav graph from back stack
        popUpTo(
            route = FORGOT_PASSWORD_NAV_GRAPH
        ) {
            inclusive = true
        }//end popUpTo

    }//end navigate

}//end navigateToLoginNavGraphWithPopForgotPasswordNavGraph

//function for create login nav graph and add destinations to it and define start destination to it
internal fun NavGraphBuilder.loginNavGraph(
    navigateToRegisterDestination: () -> Unit,
    navigateToForgotPasswordNavGraph: () -> Unit,
    popForgotPasswordNavGraph: () -> Unit,
    navigateToCodeDestination: () -> Unit,
    navigateToNewPasswordDestination: () -> Unit,
    backToLoginNavGraph: () -> Unit,
    navigateToBottomDestination: () -> Unit
) {
    //create login nav graph
    navigation(
        route = LOGIN_NAV_GRAPH_ROUTE,//define nav graph name
        startDestination = LOGIN_DESTINATION_ROUTE,//define start destination to login nav graph
        enterTransition = { enterTransitionZero() },//define enter transition method
        exitTransition = { exitTransition() },//define exist transition method
        popEnterTransition = { enterTransitionMain() },//define pop enter transition method
        popExitTransition = { exitTransition() },
    ) {
        //add destinations to login nav graph
        loginDestination(
            navigateToRegisterDestination = navigateToRegisterDestination,
            navigateToForgotPasswordNavGraph = navigateToForgotPasswordNavGraph,
            navigateToBottomDestination = navigateToBottomDestination
        )

        forgotPasswordNavGraph(
            popForgotPasswordNavGraph = popForgotPasswordNavGraph,
            navigateToCodeDestination = navigateToCodeDestination,
            navigateToNewPasswordDestination = navigateToNewPasswordDestination,
            backToLoginNavGraph = backToLoginNavGraph
        )

    }//end navigation

}//end loginNavGraph
