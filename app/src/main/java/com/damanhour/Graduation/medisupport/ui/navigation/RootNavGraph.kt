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
import com.damanhour.Graduation.medisupport.ui.navigation.child.activity.activityNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.AUTH_NAV_GRAPH_ROUTE
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.authNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.navigateToLoginNavGraphWithPopForgotPasswordNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.forgotten.navigateToForgotPasswordNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.forgotten.popForgotPasswordNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.login.navigateToLoginNavGraphWithPopWelcomeDestination
import com.damanhour.Graduation.medisupport.ui.navigation.child.bloodPressureNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.bmiNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.bottomDestination
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.navigateToBottomDestinationWithPopAuthNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.heartPredictionNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.activity.navigateToActivityNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.navigateToBloodPressureNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.navigateToBmiNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.navigateToHeartPredictionNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.activity.popActivityNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.popBloodPressureNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.popBmiNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.popHeartPredictionNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.activity.child.history.navigateToHistoryDestination
import com.damanhour.Graduation.medisupport.ui.navigation.child.activity.child.history.popHistoryDestination
import com.damanhour.Graduation.medisupport.ui.navigation.child.bloodSugarNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.navigateToBloodSugarNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.popBloodSugarNavGraph
import com.example.auth.uiElement.screens.forgotten.code.navigateToCodeDestination
import com.example.auth.uiElement.screens.forgotten.newPassword.navigateToNewPasswordDestination
import com.example.auth.uiElement.screens.register.navigateToRegisterDestination
import com.example.auth.uiElement.screens.register.popRegisterDestination
import com.example.auth.uiElement.screens.welcome.navigateToWelcomeDestination
import com.example.bloodpressure.uiElement.screens.statistics.navigateToStatisticsBloodPressureDestination
import com.example.bloodpressure.uiElement.screens.statistics.popStatisticsBloodPressureDestination
import com.example.bloodsuger.uiElement.screens.statistics.navigateToStatisticsBloodSugarDestination
import com.example.bmi.uiElement.screens.determination.navigateToDeterminationBMIDestination
import com.example.bmi.uiElement.screens.determination.popDeterminationBMIDestination
import com.example.heartprediction.uiElement.screens.prediction.navigateToPredictionHeartPredictionDestination
import com.example.heartprediction.uiElement.screens.prediction.popPredictionHeartPredictionDestination
import com.example.heartprediction.uiElement.screens.record.navigateToRecordHeartPredictionDestination
import com.example.heartprediction.uiElement.screens.record.popRecordHeartPredictionDestination
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
                    navigateToLoginNavGraph = navHostController::navigateToLoginNavGraphWithPopWelcomeDestination,
                    navigateToRegisterDestination = navHostController::navigateToRegisterDestination,
                    popRegisterDestination = navHostController::popRegisterDestination,
                    navigateToForgotPasswordNavGraph = navHostController::navigateToForgotPasswordNavGraph,
                    popForgotPasswordNavGraph = navHostController::popForgotPasswordNavGraph,
                    navigateToCodeDestination = navHostController::navigateToCodeDestination,
                    navigateToNewPasswordDestination = navHostController::navigateToNewPasswordDestination,
                    backToLoginNavGraph = navHostController::navigateToLoginNavGraphWithPopForgotPasswordNavGraph,
                    navigateToBottomDestination = navHostController::navigateToBottomDestinationWithPopAuthNavGraph
                )

                bottomDestination(
                    navigateToActivityDestination = navHostController::navigateToActivityNavGraph,
                    navigateToHeartPredictionNavGraph = navHostController::navigateToHeartPredictionNavGraph,
                    navigateToBmiNavGraph = navHostController::navigateToBmiNavGraph,
                    navigateToBloodPressureNavGraph = navHostController::navigateToBloodPressureNavGraph,
                    navigateToBloodSugarNavGraph = navHostController::navigateToBloodSugarNavGraph
                )

                activityNavGraph(
                    popActivityNavGraph = navHostController::popActivityNavGraph,
                    popHistoryDestination = navHostController::popHistoryDestination,
                    navigateToHistoryDestination = navHostController::navigateToHistoryDestination
                )

                heartPredictionNavGraph(
                    popHeartPredictionNavGraph = navHostController::popHeartPredictionNavGraph,
                    navigateToRecordHeartPredictionDestination = navHostController::navigateToRecordHeartPredictionDestination,
                    popRecordHeartPredictionDestination = navHostController::popRecordHeartPredictionDestination,
                    navigateToPredictionHeartPredictionDestination = navHostController::navigateToPredictionHeartPredictionDestination,
                    popPredictionHeartPredictionDestination = navHostController::popPredictionHeartPredictionDestination
                )

                bmiNavGraph(
                    popBmiNavGraph = navHostController::popBmiNavGraph,
                    navigateToDeterminationBMIDestination = navHostController::navigateToDeterminationBMIDestination,
                    popDeterminationBMIDestination = navHostController::popDeterminationBMIDestination
                )

                bloodPressureNavGraph(
                    popBloodPressureNavGraph = navHostController::popBloodPressureNavGraph,
                    navigateToStatisticsBloodPressureDestination = navHostController::navigateToStatisticsBloodPressureDestination,
                    popStatisticsBloodPressureDestination = navHostController::popStatisticsBloodPressureDestination
                )

                bloodSugarNavGraph(
                    navigateToStatisticsBloodSugarDestination = navHostController::navigateToStatisticsBloodSugarDestination,
                    popBloodSugarNavGraph = navHostController::popBloodSugarNavGraph
                )

            }//end AnimatedNavHost

        }//end Box

    }//end BaseScreen

}//end RootNavGraph