@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.more

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.setting.uiElement.screens.about.ABOUT_DESTINATION_ROUTE
import com.example.setting.uiElement.screens.about.aboutDestination
import com.example.setting.uiElement.screens.contactUs.CONTACT_US_DESTINATION_ROUTE
import com.example.setting.uiElement.screens.contactUs.contactUsDestination
import com.example.setting.uiElement.screens.more.MORE_DESTINATION_ROUTE
import com.example.setting.uiElement.screens.more.moreDestination
import com.example.sharedui.uiElement.components.navigation.BottomDestination
import com.example.sharedui.uiElement.components.navigation.enterTransitionZero
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.navigation

//create object from bottom destination class have icon and title for show in bottom navigation bar and route name
//child list have child destinations to home nav graph to organize bottom nav graph
val MORE_VAV_GRAPH_DATA = BottomDestination(
    route = "moreNavGraph",
    icon = com.example.sharedui.R.drawable.more,
    title = com.example.sharedui.R.string.more,
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
    popContactUsDestination: () -> Unit
) {
    //create more nav graph
    navigation(
        route = MORE_VAV_GRAPH_DATA.route,//define more nav graph name
        startDestination = MORE_DESTINATION_ROUTE,//define start destination to more nav graph
        enterTransition = { enterTransitionZero() },//define enter transition method
        exitTransition = { exitTransition() }//define exit transition method
    ) {
        //add destinations to more nav graph
        moreDestination(
            popMoreNavGraph = popMoreNavGraph,
            navigateToAboutDestination = navigateToAboutDestination,
            navigateToContactUsDestination = navigateToContactUsDestination
        )

        aboutDestination(
            popAboutDestination = popAboutDestination
        )

        contactUsDestination(
            popContactUsDestination = popContactUsDestination
        )

    }//end navigation

}//end moreNavGraph