@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.navigation.child.activityNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.AUTH_NAV_GRAPH_ROUTE
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.authNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.backToLoginNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.forgotten.navigateToForgotPasswordNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.forgotten.popForgotPasswordNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.navigateToLoginNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.bottomDestination
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.navigateToBottomDestination
import com.damanhour.Graduation.medisupport.ui.navigation.child.navigateToActivityNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.popActivityNavGraph
import com.example.activity.uiElement.screens.history.navigateToHistoryDestination
import com.example.activity.uiElement.screens.history.popHistoryDestination
import com.example.auth.uiElement.screens.forgotten.code.navigateToCodeDestination
import com.example.auth.uiElement.screens.forgotten.newPassword.navigateToNewPasswordDestination
import com.example.auth.uiElement.screens.register.navigateToRegisterDestination
import com.example.auth.uiElement.screens.register.popRegisterDestination
import com.example.auth.uiElement.screens.welcome.navigateToWelcomeDestination
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost

@RequiresApi(Build.VERSION_CODES.O)
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
                .background(
                    color = theme.background
                )
        ) {

            AnimatedNavHost(
                navController = navHostController,
                startDestination = AUTH_NAV_GRAPH_ROUTE
            ) {

                authNavGraph(
                    navigateToWelcomeDestination = navHostController::navigateToWelcomeDestination,
                    navigateToLoginNavGraph = navHostController::navigateToLoginNavGraph,
                    navigateToRegisterDestination = navHostController::navigateToRegisterDestination,
                    popRegisterDestination = navHostController::popRegisterDestination,
                    navigateToForgotPasswordNavGraph = navHostController::navigateToForgotPasswordNavGraph,
                    popForgotPasswordNavGraph = navHostController::popForgotPasswordNavGraph,
                    navigateToCodeDestination = navHostController::navigateToCodeDestination,
                    navigateToNewPasswordDestination = navHostController::navigateToNewPasswordDestination,
                    backToLoginNavGraph = navHostController::backToLoginNavGraph,
                    navigateToBottomDestination = navHostController::navigateToBottomDestination
                )

                bottomDestination(
                    navigateToActivityDestination = navHostController::navigateToActivityNavGraph
                )

                activityNavGraph(
                    popActivityNavGraph = navHostController::popActivityNavGraph,
                    popHistoryDestination = navHostController::popHistoryDestination,
                    navigateToHistoryDestination = navHostController::navigateToHistoryDestination
                )

            }//end AnimatedNavHost

        }//end Box

    }//end BaseScreen

}//end RootNavGraph