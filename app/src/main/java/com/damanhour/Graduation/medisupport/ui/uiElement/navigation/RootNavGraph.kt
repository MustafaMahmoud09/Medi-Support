@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.activity.activityNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.auth.AUTH_NAV_GRAPH_ROUTE
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.auth.authNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.auth.login.navigateToLoginNavGraphWithPopForgotPasswordNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.auth.login.forgotten.navigateToForgotPasswordNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.auth.login.forgotten.popForgotPasswordNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.auth.login.navigateToLoginNavGraphWithPopWelcomeDestination
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.bloodPressureNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.bmiNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.bottom.bottomDestination
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.bottom.navigateToBottomDestinationWithPopAuthNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.heartPredictionNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.activity.navigateToActivityNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.navigateToBloodPressureNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.navigateToBmiNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.navigateToHeartPredictionNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.activity.popActivityNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.popHeartPredictionNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.screens.history.navigateToHistoryDestination
import com.damanhour.Graduation.medisupport.ui.uiElement.screens.history.popHistoryDestination
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.bloodSugarNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.examination.child.navigateToChatNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.examination.child.navigateToOnlineRoomNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.examination.child.popChatNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.examination.child.popOnlineRoomGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.examination.examinationNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.heartRateNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.navigateToBloodPressureNavGraphWithPopActivityNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.navigateToBloodSugarNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.navigateToBloodSugarNavGraphWithPopActivityNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.navigateToBmiNavGraphWithPopActivityNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.navigateToHeartRateNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.navigateToHeartRateNavGraphWithPopActivityNavGraph
import com.example.auth.presentation.uiElement.screens.forgotten.code.navigateToCodeDestination
import com.example.auth.presentation.uiElement.screens.forgotten.newPassword.navigateToNewPasswordDestination
import com.example.auth.presentation.uiElement.screens.register.navigateToRegisterDestination
import com.example.auth.presentation.uiElement.screens.register.popRegisterDestination
import com.example.auth.presentation.uiElement.screens.welcome.navigateToWelcomeDestination
import com.example.bloodpressure.presentation.uiElement.screens.record.navigateToRecordBloodPressureDestination
import com.example.bloodpressure.presentation.uiElement.screens.record.popRecordBloodPressureDestination
import com.example.bloodpressure.presentation.uiElement.screens.statistics.navigateToStatisticsBloodPressureDestination
import com.example.bloodpressure.presentation.uiElement.screens.statistics.popStatisticsBloodPressureDestination
import com.example.bloodsugar.presentation.uiElement.screens.record.navigateToRecordBloodSugarDestination
import com.example.bloodsugar.presentation.uiElement.screens.record.popRecordBloodSugarDestination
import com.example.bloodsugar.presentation.uiElement.screens.statistics.navigateToStatisticsBloodSugarDestination
import com.example.bloodsugar.presentation.uiElement.screens.statistics.popStatisticsBloodSugarDestination
import com.example.bmi.presentation.uiElement.screens.determination.navigateToDeterminationBMIDestination
import com.example.bmi.presentation.uiElement.screens.determination.popDeterminationBMIDestination
import com.example.bmi.presentation.uiElement.screens.record.navigateToRecordBMIDestination
import com.example.bmi.presentation.uiElement.screens.record.popRecordBMIDestination
import com.damanhour.Graduation.medisupport.ui.uiElement.screens.booking_details.navigateToBookingDetailsDestinationWithPopOfflineBookingDestination
import com.damanhour.Graduation.medisupport.ui.uiElement.screens.booking_details.navigateToBookingDetailsDestinationWithPopOnlineBookingDestination
import com.damanhour.Graduation.medisupport.ui.uiElement.screens.booking_details.popBookingDetailsDestination
import com.example.offlinebooking.presentation.uiElement.screens.booking.navigateToOfflineBookingDestination
import com.example.offlinebooking.presentation.uiElement.screens.booking.popOfflineBookingDestination
import com.example.payment.presentation.uiElement.screens.payment.PAYMENT_DESTINATION_ROUTE
import com.example.presentation.uiElement.screens.chat.navigateToChatDestination
import com.example.presentation.uiElement.screens.chat.popChatDestination
import com.example.heartprediction.presentation.uiElement.screens.prediction.navigateToPredictionHeartPredictionDestination
import com.example.heartprediction.presentation.uiElement.screens.prediction.popPredictionHeartPredictionDestination
import com.example.heartprediction.presentation.uiElement.screens.record.navigateToRecordHeartPredictionDestination
import com.example.heartprediction.presentation.uiElement.screens.record.popRecordHeartPredictionDestination
import com.example.heartrate.presentation.uiElement.screens.measurement.navigateToMeasurementHeartRateDestination
import com.example.heartrate.presentation.uiElement.screens.measurement.popMeasurementHeartRateDestination
import com.example.heartrate.presentation.uiElement.screens.statistics.navigateToStatisticsHeartRateDestination
import com.example.heartrate.presentation.uiElement.screens.statistics.popStatisticsHeartRateDestination
import com.example.onlinebooking.presentation.uiElement.screens.booking.navigateToOnlineBookingDestination
import com.example.onlinebooking.presentation.uiElement.screens.booking.popOnlineBookingDestination
import com.example.room.presentation.uiElement.screens.room.navigateToOnlineRoomDestination
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
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
                startDestination = AUTH_NAV_GRAPH_ROUTE,
                enterTransition = { enterTransitionZero() },
                popExitTransition = { exitTransition() },
                popEnterTransition = { enterTransitionZero() },
                exitTransition = { exitTransition() },
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
                    navigateToBloodSugarNavGraph = navHostController::navigateToBloodSugarNavGraph,
                    navigateToHeartRateNavGraph = navHostController::navigateToHeartRateNavGraph,
                    navigateToBookingDetailsDestination = navHostController::navigateToBookingDetailsDestinationWithPopOfflineBookingDestination,
                    navigateToOnlineBookingNavGraph = navHostController::navigateToOnlineBookingDestination,
                    navigateToOfflineBookingDestination = navHostController::navigateToOfflineBookingDestination,
                )

                activityNavGraph(
                    popActivityNavGraph = navHostController::popActivityNavGraph,
                    popHistoryDestination = navHostController::popHistoryDestination,
                    navigateToHistoryDestination = navHostController::navigateToHistoryDestination,
                    navigateToHeartRateNavGraph = navHostController::navigateToHeartRateNavGraphWithPopActivityNavGraph,
                    navigateToBmiNavGraph = navHostController::navigateToBmiNavGraphWithPopActivityNavGraph,
                    navigateToBloodSugarNavGraph = navHostController::navigateToBloodSugarNavGraphWithPopActivityNavGraph,
                    navigateToBloodPressureNavGraph = navHostController::navigateToBloodPressureNavGraphWithPopActivityNavGraph
                )

                examinationNavGraph(
                    popOfflineBookingNavGraph = navHostController::popOfflineBookingDestination,
                    popOnlineBookingNavGraph = navHostController::popOnlineBookingDestination,
                    navigateToBookingDetailsDestination = navHostController::navigateToBookingDetailsDestinationWithPopOfflineBookingDestination,
                    popBookingDetailsDestination = navHostController::popBookingDetailsDestination,
                    navigateToChatNavGraph = navHostController::navigateToChatNavGraph,
                    popChatNavGraph = navHostController::popChatNavGraph,
                    navigateToChatDestination = navHostController::navigateToChatDestination,
                    popChatDestination = navHostController::popChatDestination,
                    navigateToOnlineRoomNavGraph = navHostController::navigateToOnlineRoomNavGraph,
                    popOnlineRoomGraph = navHostController::popOnlineRoomGraph,
                    navigateToBookingDetailsDestinationWithPopOnlineBookingDestination = navHostController::navigateToBookingDetailsDestinationWithPopOnlineBookingDestination,
                    navigateToOnlineRoomDestination = {

                        //execute navigate to online room and pop payment here
                        navHostController.navigateToOnlineRoomDestination(
                            poppedDestination = PAYMENT_DESTINATION_ROUTE
                        )

                    }//end scope
                )


                heartPredictionNavGraph(
                    popHeartPredictionNavGraph = navHostController::popHeartPredictionNavGraph,
                    navigateToRecordHeartPredictionDestination = navHostController::navigateToRecordHeartPredictionDestination,
                    popRecordHeartPredictionDestination = navHostController::popRecordHeartPredictionDestination,
                    navigateToPredictionHeartPredictionDestination = navHostController::navigateToPredictionHeartPredictionDestination,
                    popPredictionHeartPredictionDestination = navHostController::popPredictionHeartPredictionDestination
                )

                bmiNavGraph(
                    popRecordBMIDestination = navHostController::popRecordBMIDestination,
                    navigateToDeterminationBMIDestination = navHostController::navigateToDeterminationBMIDestination,
                    popDeterminationBMIDestination = navHostController::popDeterminationBMIDestination,
                    navigateToRecordBMIDestination = navHostController::navigateToRecordBMIDestination
                )

                bloodPressureNavGraph(
                    popRecordBloodPressureDestination = navHostController::popRecordBloodPressureDestination,
                    navigateToStatisticsBloodPressureDestination = navHostController::navigateToStatisticsBloodPressureDestination,
                    popStatisticsBloodPressureDestination = navHostController::popStatisticsBloodPressureDestination,
                    navigateToRecordBloodPressureDestination = navHostController::navigateToRecordBloodPressureDestination
                )

                bloodSugarNavGraph(
                    navigateToStatisticsBloodSugarDestination = navHostController::navigateToStatisticsBloodSugarDestination,
                    popRecordBloodSugarDestination = navHostController::popRecordBloodSugarDestination,
                    popStatisticsBloodSugarDestination = navHostController::popStatisticsBloodSugarDestination,
                    navigateToRecordBloodSugarDestination = navHostController::navigateToRecordBloodSugarDestination
                )

                heartRateNavGraph(
                    popMeasurementHeartRateDestination = navHostController::popMeasurementHeartRateDestination,
                    navigateToStatisticsHeartRateDestination = navHostController::navigateToStatisticsHeartRateDestination,
                    popStatisticsHeartRateDestination = navHostController::popStatisticsHeartRateDestination,
                    navigateToMeasurementHeartRateDestination = navHostController::navigateToMeasurementHeartRateDestination
                )

            }//end AnimatedNavHost

        }//end Box

    }//end BaseScreen

}//end RootNavGraph