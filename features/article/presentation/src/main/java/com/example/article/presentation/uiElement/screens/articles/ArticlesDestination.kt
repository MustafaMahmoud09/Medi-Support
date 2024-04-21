@file:OptIn(ExperimentalAnimationApi::class)

package com.example.article.presentation.uiElement.screens.articles

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable
import kotlin.reflect.KFunction1

const val ARTICLES_DESTINATION_ROUTE = "articlesDestination"

fun NavGraphBuilder.articlesDestination(
    popArticleNavGraph: () -> Unit,
    navigateToSingleDestination: KFunction1<Long, Unit>
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