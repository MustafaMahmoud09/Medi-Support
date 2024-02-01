@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.activity.child.activity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.components.navigation.enterTransitionZero
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

const val ACTIVITY_DESTINATION_ROUTE = "activityDestination"

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.activityDestination(
    popActivityNavGraph: () -> Unit,
    navigateToHistoryDestination: () -> Unit
) {

    composable(
        route = ACTIVITY_DESTINATION_ROUTE,
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() }
    ) {

        ActivityScreen(
            popActivityNavGraph = popActivityNavGraph,
            navigateToHistoryDestination = navigateToHistoryDestination
        )

    }//end composable

}//end activityDestination

