@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.more

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.setting.uiElement.screens.about.aboutDestination
import com.example.setting.uiElement.screens.contactUs.contactUsDestination
import com.example.setting.uiElement.screens.more.MORE_DESTINATION_ROUTE
import com.example.setting.uiElement.screens.more.moreDestination
import com.example.sharedui.uiElement.components.navigation.BottomDestination
import com.google.accompanist.navigation.animation.navigation

val MORE_VAV_GRAPH_DATA = BottomDestination(
    route = "moreNavGraph",
    icon = com.example.sharedui.R.drawable.more,
    title = com.example.sharedui.R.string.more
)

internal fun NavGraphBuilder.moreNavGraph() {

    navigation(
        route = MORE_VAV_GRAPH_DATA.route,
        startDestination = MORE_DESTINATION_ROUTE
    ) {

        moreDestination()

        aboutDestination()

        contactUsDestination()

    }//end navigation

}//end moreNavGraph