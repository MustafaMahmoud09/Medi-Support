@file:OptIn(ExperimentalFoundationApi::class)

package com.example.auth.uiElement.screens.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.auth.uiElement.components.composable.TabCircleView
import com.example.auth.uiElement.screens.welcome.child.FirstScreen
import com.example.auth.uiElement.screens.welcome.child.SecondScreen
import com.example.auth.uiElement.screens.welcome.child.ThirdScreen
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.TextNormalBrownView
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlinx.coroutines.launch

@Composable
internal fun WelcomeScreen(
    navigateToLoginNavGraph: () -> Unit
) {

    val pagerState = rememberPagerState(initialPage = 0)
    val coroutineScope = rememberCoroutineScope()
    WelcomeContent(
        pagerState = pagerState,
        onClickNext = {

            when (pagerState.currentPage) {
                0 -> {
                    coroutineScope.launch {
                        pagerState.scrollToPage(
                            page = 1
                        )
                    }//end launch
                }//end case

                1 -> {
                    coroutineScope.launch {
                        pagerState.scrollToPage(
                            page = 2
                        )
                    }//end launch
                }//end case

                2 -> {
                    navigateToLoginNavGraph()
                }//end case

            }//end when

        },//end onClickNext,
        onClickSkip = {
            coroutineScope.launch {
                pagerState.scrollToPage(
                    page = 2
                )
            }//end launch
        }//end onClickSkip
    )
}//end WelcomeScreen

@Composable
private fun WelcomeContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    pagerState: PagerState,
    onClickNext: () -> Unit,
    onClickSkip: () -> Unit
) {

    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .background(
                    color = theme.background
                )
        ) {
            val (pager, tabLayout, footer, buttonSkip) = createRefs()
            val guideLineFromTop75P = createGuidelineFromTop(.75f)

            HorizontalPager(
                state = pagerState,
                pageCount = 3,
                userScrollEnabled = false,
                modifier = Modifier
                    .constrainAs(pager) {
                        start.linkTo(parent.start)
                        bottom.linkTo(guideLineFromTop75P)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
            ) { page ->

                when (page) {

                    0 -> {

                        FirstScreen(
                            theme = theme,
                            dimen = dimen
                        )
                    }//end case

                    1 -> {

                        SecondScreen(
                            theme = theme,
                            dimen = dimen
                        )
                    }//end case

                    2 -> {

                        ThirdScreen(
                            theme = theme,
                            dimen = dimen
                        )
                    }//end case

                }//end when

            }//end HorizontalPager

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(tabLayout) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(
                            guideLineFromTop75P,
                            dimen.dimen_2.dp
                        )
                    },
                horizontalArrangement = Arrangement.Center
            ) {

                TabCircleView(
                    dimen = dimen,
                    theme = theme,
                    pagerState = pagerState,
                    currentItem = 0
                )

                Spacer(
                    modifier = Modifier
                        .width(dimen.dimen_1_5.dp)
                )

                TabCircleView(
                    dimen = dimen,
                    theme = theme,
                    pagerState = pagerState,
                    currentItem = 1
                )

                Spacer(
                    modifier = Modifier
                        .width(dimen.dimen_1_5.dp)
                )

                TabCircleView(
                    dimen = dimen,
                    theme = theme,
                    pagerState = pagerState,
                    currentItem = 2
                )

            }//end Row

            LazyColumn(
                modifier = Modifier.constrainAs(footer) {
                    start.linkTo(parent.start)
                    end.linkTo(pager.end)
                    top.linkTo(tabLayout.bottom)
                    width = Dimension.fillToConstraints
                },
                contentPadding = PaddingValues(
                    start = dimen.dimen_2.dp,
                    end = dimen.dimen_2.dp,
                    bottom = dimen.dimen_2.dp,
                    top = dimen.dimen_3.dp
                ),
                verticalArrangement = Arrangement.spacedBy(
                    space = dimen.dimen_3_5.dp
                )
            ) {

                item(
                    key = 1
                ) {

                    BasicButtonView(
                        dimen = dimen,
                        theme = theme,
                        text = if (pagerState.currentPage in 0..1) stringResource(
                            id = com.example.sharedui.R.string.next
                        ) else stringResource(
                            com.example.sharedui.R.string.get_started
                        ),
                        onClick = onClickNext,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                }//end item

                item(
                    key = 2
                ) {

                    if (pagerState.currentPage in 0..1) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {

                            TextNormalBrownView(
                                theme = theme,
                                dimen = dimen,
                                text = stringResource(
                                    com.example.sharedui.R.string.skip
                                ),
                                size = dimen.dimen_2_25,
                                onClick = onClickSkip,
                                modifier = Modifier
                            )
                        }

                    }//end Box

                }//end item

            }//end LazyColumn

        }//end ConstrainLayout

    }//end BaseScreen

}//end WelcomeContent