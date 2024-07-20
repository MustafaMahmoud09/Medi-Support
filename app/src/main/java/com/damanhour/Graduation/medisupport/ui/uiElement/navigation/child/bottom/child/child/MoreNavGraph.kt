@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.bottom.child.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.setting.presentation.uiElement.screens.about.ABOUT_DESTINATION_ROUTE
import com.example.setting.presentation.uiElement.screens.about.aboutDestination
import com.example.setting.presentation.uiElement.screens.contactUs.CONTACT_US_DESTINATION_ROUTE
import com.example.setting.presentation.uiElement.screens.contactUs.contactUsDestination
import com.example.setting.presentation.uiElement.screens.more.MORE_DESTINATION_ROUTE
import com.example.setting.presentation.uiElement.screens.more.moreDestination
import com.example.sharedui.R
import com.example.sharedui.uiElement.navigation.data.BottomDestination
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.navigation

//create object from bottom destination class have icon and title for show in bottom navigation bar and route name
//child list have child destinations to home nav graph to organize bottom nav graph
val MORE_VAV_GRAPH_DATA = BottomDestination(
    route = "moreNavGraph",
    icon = R.drawable.more,
    title = R.string.more,
    childList = listOf(
        MORE_DESTINATION_ROUTE,
        ABOUT_DESTINATION_ROUTE,
        CONTACT_US_DESTINATION_ROUTE
    )
)

//function for pop more nav graph from root nav graph
internal fun NavHostController.popMoreNavGraph() {
    //pop more nav graph
    popBackStack(
        route = MORE_VAV_GRAPH_DATA.route,
        inclusive = true
    )

}//end popMoreNavGraph

//function for create more nav graph and add destination to it and define start destination to it
internal fun NavGraphBuilder.moreNavGraph(
    popMoreNavGraph: () -> Unit,
    navigateToAboutDestination: () -> Unit,
    navigateToContactUsDestination: () -> Unit,
    popAboutDestination: () -> Unit,
    popContactUsDestination: () -> Unit,
    navigateToBookingDetailsDestination: (Int) -> Unit,
    navigateToLoginNavGraphWithPopBottomDestination: () -> Unit
) {
    //create more nav graph
    navigation(
        route = MORE_VAV_GRAPH_DATA.route,//define more nav graph name
        startDestination = MORE_DESTINATION_ROUTE,//define start destination to more nav graph
        enterTransition = { enterTransitionZero() },//define enter transition method
        exitTransition = { exitTransition() },//define exit transition method
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
    ) {
        //add destinations to more nav graph
        moreDestination(
            popMoreNavGraph = popMoreNavGraph,
            navigateToAboutDestination = navigateToAboutDestination,
            navigateToContactUsDestination = navigateToContactUsDestination,
            navigateToBookingDetailsDestination = navigateToBookingDetailsDestination,
            navigateToLoginNavGraphWithPopBottomDestination = navigateToLoginNavGraphWithPopBottomDestination
        )

        aboutDestination(
            popAboutDestination = popAboutDestination
        )

        contactUsDestination(
            popContactUsDestination = popContactUsDestination,
            navigateToLoginNavGraphWithPopBottomDestination = navigateToLoginNavGraphWithPopBottomDestination
        )

    }//end navigation

}//end moreNavGraph