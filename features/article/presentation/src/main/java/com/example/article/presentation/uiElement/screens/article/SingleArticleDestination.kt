@file:OptIn(ExperimentalAnimationApi::class)

package com.example.article.presentation.uiElement.screens.article

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.sharedui.uiElement.navigation.transitions.enterTransitionZero
import com.example.sharedui.uiElement.navigation.transitions.exitTransition
import com.google.accompanist.navigation.animation.composable

const val SINGLE_ARTICLE_DESTINATION_ROUTE = "singleArticleDestination"

fun NavHostController.popSingleArticleDestination() {

    popBackStack(
        route = "${SINGLE_ARTICLE_DESTINATION_ROUTE}/{${SingleArticleArgs.ARTICLE_ID}}",
        inclusive = true
    )

}//end popSingleArticleDestination

fun NavHostController.navigateToSingleDestination(
    articleId: Long
) {

    navigate(
        route = "$SINGLE_ARTICLE_DESTINATION_ROUTE/$articleId"
    )

}//end navigateToSingleDestination

fun NavGraphBuilder.singleArticleDestination(
    popSingleArticleDestination: () -> Unit
) {

    composable(
        route = "$SINGLE_ARTICLE_DESTINATION_ROUTE/{${SingleArticleArgs.ARTICLE_ID}}",
        enterTransition = { enterTransitionZero() },
        exitTransition = { exitTransition() },
        popExitTransition = { exitTransition() },
        popEnterTransition = { enterTransitionZero() },
        arguments = listOf(
            navArgument(SingleArticleArgs.ARTICLE_ID) {
                type = NavType.LongType
            }
        ),
    ) {

        SingleArticleScreen(
            popSingleArticleDestination = popSingleArticleDestination
        )

    }//end composable

}//end singleArticleDestination


//class created for deal with arguments is passing to this destination during the transition
class SingleArticleArgs(
    savedStateHandle: SavedStateHandle
) {

    //get arguments value here
    val articleId: Long = checkNotNull(savedStateHandle[ARTICLE_ID])

    companion object {

        //create arguments name here
        const val ARTICLE_ID = "article_id"

    }//end  companion object

}//end SingleArticleArgs