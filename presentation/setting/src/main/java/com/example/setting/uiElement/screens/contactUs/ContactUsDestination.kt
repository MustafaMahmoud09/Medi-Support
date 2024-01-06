@file:OptIn(ExperimentalAnimationApi::class)

package com.example.setting.uiElement.screens.contactUs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.setting.uiElement.screens.about.AboutScreen
import com.google.accompanist.navigation.animation.composable

const val CONTACT_US_DESTINATION_ROUTE = "contactUsDestination"

fun NavGraphBuilder.contactUsDestination() {

    composable(
        route = CONTACT_US_DESTINATION_ROUTE
    ) {

        ContactUsScreen()
    }//end composable

}//end contactUsDestination