@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.article

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.article.uiElement.screens.article.SINGLE_ARTICLE_DESTINATION_ROUTE
import com.example.article.uiElement.screens.article.singleArticleDestination
import com.example.article.uiElement.screens.articles.ARTICLES_DESTINATION_ROUTE
import com.example.article.uiElement.screens.articles.articlesDestination
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.navigation.BottomDestination
import com.example.sharedui.uiElement.components.navigation.enterTransitionZero
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.navigation

internal val ARTICLE_NAV_GRAPH_DATA = BottomDestination(
    route = "articleNavGraph",
    icon = R.drawable.article,
    title = R.string.articles,
    childList = listOf(
        ARTICLES_DESTINATION_ROUTE,
        SINGLE_ARTICLE_DESTINATION_ROUTE
    )
)

internal fun NavHostController.popArticleNavGraph() {

    popBackStack(
        route = ARTICLE_NAV_GRAPH_DATA.route,
        inclusive = true
    )

}//end popArticleNavGraph

internal fun NavGraphBuilder.articleNavGraph(
    popArticleNavGraph: () -> Unit,
    navigateToSingleDestination: () -> Unit,
    popSingleArticleDestination: () -> Unit
) {

    navigation(
        route = ARTICLE_NAV_GRAPH_DATA.route,
        startDestination = ARTICLES_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() }
    ) {

        articlesDestination(
            popArticleNavGraph = popArticleNavGraph,
            navigateToSingleDestination = navigateToSingleDestination
        )

        singleArticleDestination(
            popSingleArticleDestination = popSingleArticleDestination
        )

    }//end navigation

}//end articleNavGraph