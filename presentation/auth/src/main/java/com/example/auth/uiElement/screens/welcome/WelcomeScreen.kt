@file:OptIn(ExperimentalFoundationApi::class)

package com.example.auth.uiElement.screens.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
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
            val (pager, tabLayout, buttonNext, buttonSkip) = createRefs()
            val guideLineFromTop75P = createGuidelineFromTop(.75f)
            val guideLineFromBottom10P = createGuidelineFromBottom(.10f)


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
                    .constrainAs(buttonNext) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        end.linkTo(
                            pager.end,
                            dimen.dimen_2.dp
                        )
                        top.linkTo(
                            tabLayout.bottom,
                            dimen.dimen_3_25.dp
                        )
                        width = Dimension.fillToConstraints
                    }
                    .height(
                        dimen.dimen_6_5.dp
                    )
            )

            if (pagerState.currentPage in 0..1) {

                TextNormalBrownView(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        com.example.sharedui.R.string.skip
                    ),
                    size = dimen.dimen_2_25,
                    onClick = onClickSkip,
                    modifier = Modifier
                        .constrainAs(buttonSkip) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(
                                buttonNext.bottom,
                                dimen.dimen_3_5.dp
                            )
                        }
                )
            }

        }//end ConstrainLayout

    }//end BaseScreen

}//end WelcomeContent