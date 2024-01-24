package com.example.article.uiElement.screens.articles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.article.uiElement.components.items.ArticleSection
import com.example.sharedui.uiElement.components.composable.BackButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
internal fun ArticlesScreen(
    popArticleNavGraph: () -> Unit,
    navigateToSingleDestination: () -> Unit
) {

    ArticlesContent(
        onClickBack = popArticleNavGraph,
        onClickReadNow = navigateToSingleDestination
    )
}//end ArticlesScreen

@Composable
private fun ArticlesContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickBack: () -> Unit,
    onClickReadNow: () -> Unit
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            )
    ) {
        val (backButtonId, titleId, articlesId) = createRefs()

        BackButtonView(
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
                        dimen.dimen_4.dp
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
                        dimen.dimen_4.dp
                    )
                }//end constrainAs
        )

        LazyColumn(
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
                },
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
                    src = if (it % 2 == 0) painterResource(id = com.example.sharedui.R.drawable.test_article_1) else painterResource(
                        id = com.example.sharedui.R.drawable.test_article_2
                    ),
                    title = "Heart Rate",
                    onClickOnButton = onClickReadNow,
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end items

        }//end LazyColumn

    }//end ConstraintLayout

}//end ArticlesContent