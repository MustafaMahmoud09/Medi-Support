@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.article.articleNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.home.HOME_VAV_GRAPH_DATA
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.home.homeNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.more.moreNavGraph
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
    popContactUsDestination: () -> Unit,
    popArticleNavGraph: () -> Unit,
    navigateToSingleDestination: () -> Unit,
    popSingleArticleDestination: () -> Unit
) {

    AnimatedNavHost(
        navController = navHostController,
        startDestination = HOME_VAV_GRAPH_DATA.route
    ) {

        homeNavGraph()

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
            popContactUsDestination = popContactUsDestination
        )

    }//end AnimatedNavHost

}//end BottomNavGraph