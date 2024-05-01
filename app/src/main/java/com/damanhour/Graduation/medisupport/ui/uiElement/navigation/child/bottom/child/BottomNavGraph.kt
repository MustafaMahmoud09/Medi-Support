@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.bottom.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.bottom.child.child.home.HOME_VAV_GRAPH_DATA
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.bottom.child.child.articleNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.bottom.child.child.home.homeNavGraph
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.bottom.child.child.moreNavGraph
import com.example.setting.presentation.uiElement.screens.profile.profileDestination
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.AnimatedNavHost
import kotlin.reflect.KFunction1

@Composable
internal fun BottomNavGraph(
    navHostController: NavHostController,
    popProfileDestination: () -> Unit,
    popMoreNavGraph: () -> Unit,
    navigateToAboutDestination: () -> Unit,
    navigateToContactUsDestination: () -> Unit,
    popAboutDestination: () -> Unit,
    popContactUsDestination: () -> Unit,
    popArticleNavGraph: () -> Unit,
    navigateToSingleDestination: KFunction1<Long, Unit>,
    popSingleArticleDestination: () -> Unit,
    navigateToHeartPredictionNavGraph: () -> Unit,
    navigateToAddReminderDestination: () -> Unit,
    popAddReminderDestination: () -> Unit,
    navigateToReminderRecordsDestination: () -> Unit,
    popReminderRecordsDestination: () -> Unit,
    navigateToBmiNavGraph: () -> Unit,
    navigateToBloodPressureNavGraph: () -> Unit,
    navigateToBloodSugarNavGraph: () -> Unit,
    navigateToHeartRateNavGraph: () -> Unit,
    navigateToOnlineBookingNavGraph: (Int) -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit,
    navigateToOfflineBookingDestination: (Int) -> Unit
) {

    AnimatedNavHost(
        navController = navHostController,
        startDestination = HOME_VAV_GRAPH_DATA.route,
        enterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
    ) {

        homeNavGraph(
            navigateToHeartPredictionNavGraph = navigateToHeartPredictionNavGraph,
            navigateToAddReminderDestination = navigateToAddReminderDestination,
            popAddReminderDestination = popAddReminderDestination,
            navigateToReminderRecordsDestination = navigateToReminderRecordsDestination,
            popReminderRecordsDestination = popReminderRecordsDestination,
            navigateToBmiNavGraph = navigateToBmiNavGraph,
            navigateToBloodPressureNavGraph = navigateToBloodPressureNavGraph,
            navigateToBloodSugarNavGraph = navigateToBloodSugarNavGraph,
            navigateToHeartRateNavGraph = navigateToHeartRateNavGraph,
            navigateToOnlineBookingNavGraph = navigateToOnlineBookingNavGraph,
            navigateToBookingDetailsDestination = navigateToBookingDetailsDestination,
            navigateToOfflineBookingDestination = navigateToOfflineBookingDestination
        )

        articleNavGraph(
            popArticleNavGraph = popArticleNavGraph,
            navigateToSingleDestination = navigateToSingleDestination,
            popSingleArticleDestination = popSingleArticleDestination
        )

        profileDestination(
            popProfileDestination = popProfileDestination
        )

        moreNavGraph(
            popMoreNavGraph = popMoreNavGraph,
            navigateToAboutDestination = navigateToAboutDestination,
            navigateToContactUsDestination = navigateToContactUsDestination,
            popAboutDestination = popAboutDestination,
            popContactUsDestination = popContactUsDestination,
            navigateToBookingDetailsDestination = navigateToBookingDetailsDestination
        )

    }//end AnimatedNavHost

}//end BottomNavGraph