@file:OptIn(ExperimentalAnimationApi::class)

package com.example.article.uiElement.screens.articles

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.sharedui.uiElement.components.navigation.BottomDestination
import com.google.accompanist.navigation.animation.composable

val ARTICLES_DESTINATION_DATA = BottomDestination(
    route = "articlesDestination",
    icon = com.example.sharedui.R.drawable.article,
    title = com.example.sharedui.R.string.articles
)

fun NavGraphBuilder.articlesDestination() {

    composable(
        route = ARTICLES_DESTINATION_DATA.route
    ) {

        ArticlesScreen()

    }//end composable

}//end articlesDestination