@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.forgotten


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.auth.uiElement.screens.forgotten.code.codeDestination
import com.example.auth.uiElement.screens.forgotten.email.EMAIL_DESTINATION_ROUTE
import com.example.auth.uiElement.screens.forgotten.email.emailDestination
import com.example.auth.uiElement.screens.forgotten.newPassword.newPasswordDestination
import com.example.sharedui.uiElement.components.navigation.enterTransitionZero
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.navigation

//nav graph name
internal const val FORGOT_PASSWORD_NAV_GRAPH = "forgotPasswordNavGraph"

//function for add forgot password nav graph to top root nav graph
internal fun NavHostController.navigateToForgotPasswordNavGraph() {
    //navigate to forgot password nav graph
    navigate(
        route = FORGOT_PASSWORD_NAV_GRAPH
    )
}//end navigateToForgotPasswordNavGraph

//function for pop forgot password nav graph from root nav graph
internal fun NavHostController.popForgotPasswordNavGraph() {
    //pop forgot password nav graph
    popBackStack(
        route = FORGOT_PASSWORD_NAV_GRAPH,
        inclusive = true
    )
}//end popForgotPasswordNavGraph

//function for create forgot password nav graph and add destinations to it and define start destination to it
internal fun NavGraphBuilder.forgotPasswordNavGraph(
    popForgotPasswordNavGraph: () -> Unit,
    navigateToCodeDestination: () -> Unit,
    navigateToNewPasswordDestination: () -> Unit,
    backToLoginNavGraph: () -> Unit
) {
    //create forgot password nav graph
    navigation(
        route = FORGOT_PASSWORD_NAV_GRAPH,//define nav graph name
        startDestination = EMAIL_DESTINATION_ROUTE,//define start destination to forgot password nav graph
        enterTransition = { enterTransitionZero() },//define enter transition method
        popExitTransition = { exitTransition() }//define pop exit transition method
    ) {
        //add destinations to forgot password nav graph
        emailDestination(
            popForgotPasswordNavGraph = popForgotPasswordNavGraph,
            navigateToCodeDestination = navigateToCodeDestination
        )

        codeDestination(
            navigateToNewPasswordDestination = navigateToNewPasswordDestination
        )

        newPasswordDestination(
            backToLoginNavGraph = backToLoginNavGraph
        )

    }//end navigation

}//end forgotPasswordNavGraph

