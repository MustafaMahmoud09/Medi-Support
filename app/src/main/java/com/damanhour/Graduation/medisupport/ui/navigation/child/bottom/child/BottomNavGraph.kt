@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.more.moreNavGraph
import com.example.article.uiElement.screens.articles.articlesDestination
import com.example.booking.uiElement.screens.search.DOCTOR_SEARCH_DESTINATION_DATA
import com.example.booking.uiElement.screens.search.doctorSearchDestination
import com.example.profile.uiElement.screens.profile.profileDestination
import com.google.accompanist.navigation.animation.AnimatedNavHost


@Composable
internal fun BottomNavGraph(
    navHostController: NavHostController,
    popProfileDestination: () -> Unit,
    popMoreNavGraph: () -> Unit,
    navigateToAboutDestination: () -> Unit,
    navigateToContactUsDestination: () -> Unit,
    popAboutDestination: () -> Unit,
    popContactUsDestination: () -> Unit
) {

    AnimatedNavHost(
        navController = navHostController,
        startDestination = DOCTOR_SEARCH_DESTINATION_DATA.route
    ) {

        doctorSearchDestination()

        articlesDestination()

        profileDestination(
            popProfileDestination = popProfileDestination
        )

        moreNavGraph(
            popMoreNavGraph = popMoreNavGraph,
            navigateToAboutDestination = navigateToAboutDestination,
            navigateToContactUsDestination = navigateToContactUsDestination,
            popAboutDestination = popAboutDestination,
            popContactUsDestination = popContactUsDestination
        )

    }//end AnimatedNavHost

}//end BottomNavGraph