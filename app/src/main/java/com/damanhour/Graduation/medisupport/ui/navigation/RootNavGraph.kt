@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.AUTH_NAV_GRAPH_ROUTE
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.authNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.forgotten.navigateToForgotPasswordNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.forgotten.popForgotPasswordNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.navigateToLoginNavGraph
import com.example.auth.uiElement.screens.forgotten.code.navigateToCodeDestination
import com.example.auth.uiElement.screens.forgotten.newPassword.navigateToNewPasswordDestination
import com.example.auth.uiElement.screens.register.navigateToRegisterDestination
import com.example.auth.uiElement.screens.register.popRegisterDestination
import com.example.auth.uiElement.screens.welcome.navigateToWelcomeDestination
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
//import com.damanhour.Graduation.medisupport.ui.navigation.child.bloodPressureNavGraph
//import com.damanhour.Graduation.medisupport.ui.navigation.child.bloodSugarNavGraph
//import com.damanhour.Graduation.medisupport.ui.navigation.child.bmiNavGraph
//import com.damanhour.Graduation.medisupport.ui.navigation.child.bookingNavGraph
//import com.damanhour.Graduation.medisupport.ui.navigation.child.heartPredictionNavGraph
//import com.damanhour.Graduation.medisupport.ui.navigation.child.heartRateNavGraph
//import com.damanhour.Graduation.medisupport.ui.navigation.child.reminderNavGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost

@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    theme: CustomTheme = MediSupportAppTheme()
) {

    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            AnimatedNavHost(
                navController = navHostController,
                startDestination = AUTH_NAV_GRAPH_ROUTE
            ) {

                authNavGraph(
                    navHostController = navHostController,
                    navigateToWelcomeDestination = navHostController::navigateToWelcomeDestination,
                    navigateToLoginNavGraph = navHostController::navigateToLoginNavGraph,
                    navigateToRegisterDestination = navHostController::navigateToRegisterDestination,
                    popRegisterDestination = navHostController::popRegisterDestination,
                    navigateToForgotPasswordNavGraph = navHostController::navigateToForgotPasswordNavGraph,
                    popForgotPasswordNavGraph = navHostController::popForgotPasswordNavGraph,
                    navigateToCodeDestination = navHostController::navigateToCodeDestination,
                    navigateToNewPasswordDestination = navHostController::navigateToNewPasswordDestination
                )

            }//end AnimatedNavHost

        }//end Box

    }//end BaseScreen

}//end RootNavGraph