@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.bottom

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.navigation.child.activity.ACTIVITY_NAV_GRAPH_DATA
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.BottomNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.child.ARTICLE_NAV_GRAPH_DATA
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.child.popArticleNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.child.home.HOME_VAV_GRAPH_DATA
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.child.MORE_VAV_GRAPH_DATA
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.child.home.child.navigateToAddReminderNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.child.home.child.popAddReminderNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.child.popMoreNavGraph
import com.example.article.uiElement.screens.article.navigateToSingleDestination
import com.example.article.uiElement.screens.article.popSingleArticleDestination
import com.example.profile.uiElement.screens.profile.PROFILE_DESTINATION_DATA
import com.example.profile.uiElement.screens.profile.popProfileDestination
import com.example.reminder.uiElement.screens.records.navigateToReminderRecordsDestination
import com.example.reminder.uiElement.screens.records.popReminderRecordsDestination
import com.example.setting.uiElement.screens.about.navigateToAboutDestination
import com.example.setting.uiElement.screens.about.popAboutDestination
import com.example.setting.uiElement.screens.contactUs.navigateToContactUsDestination
import com.example.setting.uiElement.screens.contactUs.popContactUsDestination
import com.example.sharedui.uiElement.components.items.BottomNavigationSection
import com.example.sharedui.uiElement.containers.navigation.BottomDestination
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlin.reflect.KFunction0

@Composable
internal fun BottomScreen(
    navHostController: NavHostController = rememberAnimatedNavController(),
    navigateToActivityDestination: () -> Unit,
    navigateToHeartPredictionNavGraph: () -> Unit,
    navigateToBmiNavGraph: () -> Unit,
    navigateToBloodPressureNavGraph: () -> Unit,
    navigateToBloodSugarNavGraph: () -> Unit,
    navigateToHeartRateNavGraph: KFunction0<Unit>,
    navigateToBookingNavGraph: (Boolean, Int) -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit
) {

    val items = listOf(
        HOME_VAV_GRAPH_DATA,
        ACTIVITY_NAV_GRAPH_DATA,
        ARTICLE_NAV_GRAPH_DATA,
        PROFILE_DESTINATION_DATA,
        MORE_VAV_GRAPH_DATA
    )

    BottomContent(
        navHostController = navHostController,
        items = items,
        navigateToActivityDestination = navigateToActivityDestination,
        popProfileDestination = navHostController::popProfileDestination,
        popMoreNavGraph = navHostController::popMoreNavGraph,
        navigateToAboutDestination = navHostController::navigateToAboutDestination,
        navigateToContactUsDestination = navHostController::navigateToContactUsDestination,
        popAboutDestination = navHostController::popAboutDestination,
        popContactUsDestination = navHostController::popContactUsDestination,
        popArticleNavGraph = navHostController::popArticleNavGraph,
        navigateToSingleDestination = navHostController::navigateToSingleDestination,
        popSingleArticleDestination = navHostController::popSingleArticleDestination,
        navigateToHeartPredictionNavGraph = navigateToHeartPredictionNavGraph,
        navigateToAddReminderDestination = navHostController::navigateToAddReminderNavGraph,
        popAddReminderDestination = navHostController::popAddReminderNavGraph,
        navigateToReminderRecordsDestination = navHostController::navigateToReminderRecordsDestination,
        popReminderRecordsDestination = navHostController::popReminderRecordsDestination,
        navigateToBmiNavGraph = navigateToBmiNavGraph,
        navigateToBloodPressureNavGraph = navigateToBloodPressureNavGraph,
        navigateToBloodSugarNavGraph = navigateToBloodSugarNavGraph,
        navigateToHeartRateNavGraph = navigateToHeartRateNavGraph,
        navigateToBookingNavGraph = navigateToBookingNavGraph,
        navigateToBookingDetailsDestination = navigateToBookingDetailsDestination
    )
}//end BottomScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun BottomContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    navHostController: NavHostController,
    items: List<BottomDestination>,
    navigateToActivityDestination: () -> Unit,
    popProfileDestination: () -> Unit,
    popMoreNavGraph: () -> Unit,
    navigateToAboutDestination: () -> Unit,
    navigateToContactUsDestination: () -> Unit,
    popAboutDestination: () -> Unit,
    popContactUsDestination: () -> Unit,
    popArticleNavGraph: () -> Unit,
    navigateToSingleDestination: () -> Unit,
    popSingleArticleDestination: () -> Unit,
    navigateToHeartPredictionNavGraph: () -> Unit,
    navigateToAddReminderDestination: () -> Unit,
    popAddReminderDestination: () -> Unit,
    navigateToReminderRecordsDestination: () -> Unit,
    popReminderRecordsDestination: () -> Unit,
    navigateToBmiNavGraph: () -> Unit,
    navigateToBloodPressureNavGraph: () -> Unit,
    navigateToBloodSugarNavGraph: () -> Unit,
    navigateToHeartRateNavGraph: KFunction0<Unit>,
    navigateToBookingNavGraph: (Boolean, Int) -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit
) {

    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        Scaffold(
            modifier = Modifier
                .safeDrawingPadding()
                .background(
                    color = theme.background
                )
                .fillMaxSize(),
            bottomBar = {

                BottomAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(
                            dimen.dimen_8_5.dp
                        )
                        .background(
                            color = theme.background
                        ),
                    contentPadding = PaddingValues(
                        dimen.dimen_0.dp
                    ),
                    backgroundColor = theme.background,
                ) {

                    BottomNavigationSection(
                        navController = navHostController,
                        routeNull = ACTIVITY_NAV_GRAPH_DATA.route,
                        ifRouteNull = navigateToActivityDestination,
                        items = items,
                        dimen = dimen,
                        theme = theme
                    )

                }//end BottomAppBar

            }//end bottomBar

        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = theme.background
                    )
                    .padding(
                        bottom = dimen.dimen_8_5.dp
                    )
            ) {

                BottomNavGraph(
                    navHostController = navHostController,
                    popProfileDestination = popProfileDestination,
                    popMoreNavGraph = popMoreNavGraph,
                    navigateToAboutDestination = navigateToAboutDestination,
                    navigateToContactUsDestination = navigateToContactUsDestination,
                    popAboutDestination = popAboutDestination,
                    popContactUsDestination = popContactUsDestination,
                    popArticleNavGraph = popArticleNavGraph,
                    navigateToSingleDestination = navigateToSingleDestination,
                    popSingleArticleDestination = popSingleArticleDestination,
                    navigateToHeartPredictionNavGraph = navigateToHeartPredictionNavGraph,
                    navigateToAddReminderDestination = navigateToAddReminderDestination,
                    popAddReminderDestination = popAddReminderDestination,
                    navigateToReminderRecordsDestination = navigateToReminderRecordsDestination,
                    popReminderRecordsDestination = popReminderRecordsDestination,
                    navigateToBmiNavGraph = navigateToBmiNavGraph,
                    navigateToBloodPressureNavGraph = navigateToBloodPressureNavGraph,
                    navigateToBloodSugarNavGraph = navigateToBloodSugarNavGraph,
                    navigateToHeartRateNavGraph = navigateToHeartRateNavGraph,
                    navigateToBookingNavGraph = navigateToBookingNavGraph,
                    navigateToBookingDetailsDestination = navigateToBookingDetailsDestination
                )

            }//end Box

        }//end Scaffold

    }//end BaseScreen

}//end BottomContent