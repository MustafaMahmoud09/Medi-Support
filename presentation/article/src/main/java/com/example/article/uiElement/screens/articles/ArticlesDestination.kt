@file:OptIn(ExperimentalAnimationApi::class)

package com.example.article.uiElement.screens.articles

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.containers.navigation.enterTransitionZero
import com.example.sharedui.uiElement.containers.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

const val ARTICLES_DESTINATION_ROUTE = "articlesDestination"

fun NavGraphBuilder.articlesDestination(
    popArticleNavGraph: () -> Unit,
    navigateToSingleDestination: () -> Unit
) {

    composable(
        route = ARTICLES_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
    ) {

        ArticlesScreen(
            popArticleNavGraph = popArticleNavGraph,
            navigateToSingleDestination = navigateToSingleDestination
        )

    }//end composable

}//end articlesDestination