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
import com.example.sharedui.uiElement.components.navigation.enterTransition
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.navigation

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

internal fun NavHostController.popMoreNavGraph() {

    popBackStack(
        route = MORE_VAV_GRAPH_DATA.route,
        inclusive = true
    )

}//end popMoreNavGraph

internal fun NavGraphBuilder.moreNavGraph(
    popMoreNavGraph: () -> Unit,
    navigateToAboutDestination: () -> Unit,
    navigateToContactUsDestination: () -> Unit,
    popAboutDestination: () -> Unit,
    popContactUsDestination: () -> Unit
) {

    navigation(
        route = MORE_VAV_GRAPH_DATA.route,
        startDestination = MORE_DESTINATION_ROUTE,
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() }
    ) {

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