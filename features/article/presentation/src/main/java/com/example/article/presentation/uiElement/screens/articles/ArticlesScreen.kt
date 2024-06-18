@file:OptIn(ExperimentalMaterialApi::class)

package com.example.article.presentation.uiElement.screens.articles

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.article.domain.model.TitleArticleModel
import com.example.article.presentation.uiElement.components.items.ArticleSection
import com.example.article.presentation.uiState.state.ArticlesUiState
import com.example.article.presentation.uiState.viewModel.ArticlesViewModel
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction1

@Composable
internal fun ArticlesScreen(
    viewModel: ArticlesViewModel = hiltViewModel(),
    popArticleNavGraph: () -> Unit,
    navigateToSingleDestination: KFunction1<Long, Unit>
) {
    //collect screen state here
    val state = viewModel.state.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.value.refreshState,
        onRefresh = viewModel::onRefreshArticles
    )

    ArticlesContent(
        onClickBack = popArticleNavGraph,
        onClickReadNow = navigateToSingleDestination,
        uiState = state.value,
        onArticlesBackupCreated = viewModel::onArticlesBackupCreated,
        pullRefreshState = pullRefreshState,
        articlesState = state.value.articles?.collectAsLazyPagingItems(),
        backupArticlesState = state.value.cacheArticles?.collectAsLazyPagingItems()
    )
}//end ArticlesScreen

@Composable
private fun ArticlesContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickBack: () -> Unit,
    onClickReadNow: KFunction1<Long, Unit>,
    uiState: ArticlesUiState,
    articlesState: LazyPagingItems<TitleArticleModel>?,
    onArticlesBackupCreated: KFunction0<Unit>,
    pullRefreshState: PullRefreshState,
    backupArticlesState: LazyPagingItems<TitleArticleModel>?
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            )
    ) {
        val (backButtonId, titleId, articlesId, placeHolderArticlesId) = createRefs()

        IconButtonView(
            dimen = dimen,
            theme = theme,
            onClick = onClickBack,
            modifier = Modifier
                .constrainAs(backButtonId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_3.dp
                    )
                }//end constrainAs
        )

        TextBoldView(
            theme = theme,
            dimen = dimen,
            text = stringResource(
                id = com.example.sharedui.R.string.articles
            ),
            size = dimen.dimen_2_25,
            modifier = Modifier
                .constrainAs(titleId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        parent.top,
                        dimen.dimen_3.dp
                    )
                }//end constrainAs
        )

        //if request state is loading show placeholder
        AnimatedVisibility(
            visible = articlesState?.loadState?.refresh !is LoadState.NotLoading &&
                    backupArticlesState?.loadState?.refresh !is LoadState.NotLoading,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 50
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 50
                )
            ),
            modifier = Modifier
                .constrainAs(placeHolderArticlesId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        backButtonId.bottom,
                        dimen.dimen_2.dp
                    )
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(
                    start = dimen.dimen_2.dp,
                    end = dimen.dimen_2.dp,
                    top = dimen.dimen_1.dp,
                    bottom = dimen.dimen_2.dp
                ),
                verticalArrangement = Arrangement.spacedBy(
                    space = dimen.dimen_2_25.dp
                )
            ) {

                items(
                    count = 10
                ) {

                    ArticleSection(
                        dimen = dimen,
                        theme = theme,
                        article = uiState.placeHolderArticle,
                        placeHolderState = true,
                        onClickOnButton = {},
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                }
            }//end items

        }//end LazyColumn

        AnimatedVisibility(
            visible = articlesState?.loadState?.refresh is LoadState.NotLoading ||
                    backupArticlesState?.loadState?.refresh is LoadState.NotLoading,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 50
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 50
                )
            ),
            modifier = Modifier
                .constrainAs(articlesId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        backButtonId.bottom,
                        dimen.dimen_2.dp
                    )
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        ) {

            val subArticles =
                if (articlesState?.loadState?.refresh is LoadState.NotLoading) {
                    articlesState
                }//end if
                else {
                    backupArticlesState
                }//end else

            Box(
                modifier = Modifier
                    .pullRefresh(
                        state = pullRefreshState
                    )
            ){

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(
                        start = dimen.dimen_2.dp,
                        end = dimen.dimen_2.dp,
                        top = dimen.dimen_1.dp,
                        bottom = dimen.dimen_2.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(
                        space = dimen.dimen_2_25.dp
                    )
                ) {

                    subArticles?.let { articles ->
                        items(
                            count = articles.itemCount
                        ) { count ->

                            ArticleSection(
                                dimen = dimen,
                                theme = theme,
                                article = articles[count]!!,
                                onClickOnButton = onClickReadNow,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                        }
                    }//end items

                }//end LazyColumn

                PullRefreshIndicator(
                    refreshing = uiState.refreshState,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter),
                )

            }//end Box

        }//end AnimatedVisibility

    }//end ConstraintLayout


    LaunchedEffect(
        key1 = articlesState?.loadState?.refresh
    ) {

        if (articlesState?.loadState?.refresh is LoadState.NotLoading) {

            onArticlesBackupCreated()

        }//end if

    }//end LaunchedEffect

}//end ArticlesContent