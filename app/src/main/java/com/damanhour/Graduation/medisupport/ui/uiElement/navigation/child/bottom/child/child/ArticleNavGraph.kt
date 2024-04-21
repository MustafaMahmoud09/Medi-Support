@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.uiElement.navigation.child.bottom.child.child

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.article.presentation.uiElement.screens.article.SINGLE_ARTICLE_DESTINATION_ROUTE
import com.example.article.presentation.uiElement.screens.article.singleArticleDestination
import com.example.article.presentation.uiElement.screens.articles.ARTICLES_DESTINATION_ROUTE
import com.example.article.presentation.uiElement.screens.articles.articlesDestination
import com.example.sharedui.R
import com.example.sharedui.uiElement.navigation.data.BottomDestination
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.navigation
import kotlin.reflect.KFunction1

//create object from bottom destination class have icon and title for show in bottom navigation bar and route name
//child list have child destinations to article nav graph to organize bottom nav graph
internal val ARTICLE_NAV_GRAPH_DATA = BottomDestination(
    route = "articleNavGraph",
    icon = R.drawable.article,
    title = R.string.articles,
    childList = listOf(
        ARTICLES_DESTINATION_ROUTE,
        SINGLE_ARTICLE_DESTINATION_ROUTE
    )
)

//function for pop article nav graph from root nav graph
internal fun NavHostController.popArticleNavGraph() {
    //pop article nav graph
    popBackStack(
        route = ARTICLE_NAV_GRAPH_DATA.route,
        inclusive = true
    )

}//end popArticleNavGraph

//function for create article nav graph and add destination to it and define start destination to it
internal fun NavGraphBuilder.articleNavGraph(
    popArticleNavGraph: () -> Unit,
    navigateToSingleDestination: KFunction1<Long, Unit>,
    popSingleArticleDestination: () -> Unit
) {
    //create article nav graph
    navigation(
        route = ARTICLE_NAV_GRAPH_DATA.route,//define article nav graph name
        startDestination = ARTICLES_DESTINATION_ROUTE,//define start destination to article nav graph
        enterTransition = { enterTransitionZero() },//define enter transition method
        exitTransition = { exitTransition() },//define exit transition method
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
    ) {
        //add destinations to article nav graph
        articlesDestination(
            popArticleNavGraph = popArticleNavGraph,
            navigateToSingleDestination = navigateToSingleDestination
        )

        singleArticleDestination(
            popSingleArticleDestination = popSingleArticleDestination
        )

    }//end navigation

}//end articleNavGraph