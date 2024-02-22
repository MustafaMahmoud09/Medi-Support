@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.bottom

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.navigation.child.auth.AUTH_NAV_GRAPH_ROUTE
import com.example.sharedui.uiElement.containers.navigation.enterTransitionZero
import com.example.sharedui.uiElement.containers.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable
import kotlin.reflect.KFunction0

//destination name
const val BOTTOM_DESTINATION_ROUTE = "bottomDestination"

//function for add bottom destination to top root nav graph and pop auth nav graph from back stack
internal fun NavHostController.navigateToBottomDestinationWithPopAuthNavGraph() {
    //navigate to bottom nav graph
    navigate(
        route = BOTTOM_DESTINATION_ROUTE
    ) {
        //pop auth nav graph
        popUpTo(
            route = AUTH_NAV_GRAPH_ROUTE
        ) {
            inclusive = true
        }//end popUpTo

    }//end navigate

}//end navigateToBottomDestinationWithPopAuthNavGraph

//function for create bottom destination and add bottom screen to it
internal fun NavGraphBuilder.bottomDestination(
    navigateToActivityDestination: () -> Unit,
    navigateToHeartPredictionNavGraph: () -> Unit,
    navigateToBmiNavGraph: () -> Unit,
    navigateToBloodPressureNavGraph: () -> Unit,
    navigateToBloodSugarNavGraph: () -> Unit,
    navigateToHeartRateNavGraph: KFunction0<Unit>,
    navigateToBookingNavGraph: (Boolean, Int) -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit
) {
    //create bottom destination
    composable(
        route = BOTTOM_DESTINATION_ROUTE,//define bottom destination name
        enterTransition = { enterTransitionZero() },//define enter transition method
        exitTransition = { exitTransition() },//define exit transition method
        popEnterTransition = { enterTransitionZero() },//define pop enter transition method
        popExitTransition = { exitTransition() },
    ) {
        //add bottom screen to bottom destination
        BottomScreen(
            navigateToActivityDestination = navigateToActivityDestination,
            navigateToHeartPredictionNavGraph = navigateToHeartPredictionNavGraph,
            navigateToBmiNavGraph = navigateToBmiNavGraph,
            navigateToBloodPressureNavGraph = navigateToBloodPressureNavGraph,
            navigateToBloodSugarNavGraph = navigateToBloodSugarNavGraph,
            navigateToHeartRateNavGraph = navigateToHeartRateNavGraph,
            navigateToBookingNavGraph = navigateToBookingNavGraph,
            navigateToBookingDetailsDestination = navigateToBookingDetailsDestination
        )
    }//end composable

}//end bottomDestination

