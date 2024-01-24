@file:OptIn(ExperimentalAnimationApi::class)

package com.example.article.uiElement.screens.article

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.sharedui.uiElement.components.navigation.enterTransitionZero
import com.example.sharedui.uiElement.components.navigation.exitTransition
import com.google.accompanist.navigation.animation.composable

const val SINGLE_ARTICLE_DESTINATION_ROUTE = "singleArticleDestination"

fun NavHostController.popSingleArticleDestination() {

    popBackStack(
        route = SINGLE_ARTICLE_DESTINATION_ROUTE,
        inclusive = true
    )

}//end popSingleArticleDestination

fun NavHostController.navigateToSingleDestination() {

    navigate(
        route = SINGLE_ARTICLE_DESTINATION_ROUTE
    )

}//end navigateToSingleDestination

fun NavGraphBuilder.singleArticleDestination(
    popSingleArticleDestination: () -> Unit
) {

    composable(
        route = SINGLE_ARTICLE_DESTINATION_ROUTE,
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() }
    ) {

        SingleArticleScreen(
            popSingleArticleDestination = popSingleArticleDestination
        )

    }//end composable

}//end singleArticleDestination