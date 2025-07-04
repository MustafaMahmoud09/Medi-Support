package com.example.article.presentation.uiElement.screens.article

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.article.presentation.uiState.state.ArticleUiState
import com.example.article.presentation.uiState.viewModel.ArticleViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.ServerLoadImageView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
internal fun SingleArticleScreen(
    viewModel: ArticleViewModel = hiltViewModel(),
    popSingleArticleDestination: () -> Unit
) {
    //collect screen state here
    val state = viewModel.state.collectAsState()

    SingleArticleContent(
        onClickBack = popSingleArticleDestination,
        uiState = state.value
    )

    //make launch effect for observe article deleted state
    //for pop destination when deletion
    LaunchedEffect(
        key1 = state.value.articleDeleted
    ) {

        if (state.value.articleDeleted) {
            popSingleArticleDestination()
        }//end if

    }//end LaunchedEffect

}//end SingleArticleScreen

@Composable
private fun SingleArticleContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickBack: () -> Unit,
    uiState: ArticleUiState
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            )
    ) {
        val (backButtonId, titleId, contentId) = createRefs()

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
                id = R.string.articles
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

        LazyColumn(
            modifier = Modifier
                .constrainAs(contentId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        backButtonId.bottom,
                        dimen.dimen_2.dp
                    )
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            contentPadding = PaddingValues(
                start = dimen.dimen_2.dp,
                end = dimen.dimen_2.dp,
                top = dimen.dimen_1_5.dp,
                bottom = dimen.dimen_2.dp
            ),
            verticalArrangement = Arrangement.spacedBy(
                space = dimen.dimen_2_25.dp
            )
        ) {

            item(
                key = 1
            ) {

                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    val (categoryId, imageId, titleArticleId, bodyId) = createRefs()

                    TextBoldView(
                        theme = theme,
                        dimen = dimen,
                        text = uiState.article.title,
                        size = dimen.dimen_2,
                        color = theme.redDark,
                        textAlign = null,
                        modifier = Modifier
                            .constrainAs(categoryId) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                            }//end constrainAs
                    )

                    Box(
                        modifier = Modifier
                            .constrainAs(imageId) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(
                                    categoryId.bottom,
                                    dimen.dimen_2_25.dp
                                )
                                width = Dimension.fillToConstraints
                            }
                            .aspectRatio(
                                ratio = 3f
                            )
                    ) {

                        ServerLoadImageView(
                            theme = theme,
                            dimen = dimen,
                            imageUrl = uiState.article.image,
                            modifier = Modifier
                                .fillMaxSize()
                        )

                    }

                    TextNormalView(
                        theme = theme,
                        dimen = dimen,
                        text = uiState.article.body,
                        size = dimen.dimen_2,
                        fontColor = theme.grayDark,
                        textAlign = null,
                        modifier = Modifier
                            .constrainAs(bodyId) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(
                                    imageId.bottom,
                                    dimen.dimen_2.dp
                                )
                                width = Dimension.fillToConstraints
                            }
                    )

                }//end ConstraintLayout

            }//end item

        }//end LazyColumn

    }//end ConstraintLayout

}//end SingleArticleContent