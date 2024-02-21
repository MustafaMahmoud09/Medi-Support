@file:OptIn(ExperimentalFoundationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.activity.child.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
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
import com.example.bmi.uiElement.screens.activities.BMIHistoryScreen
import com.example.bloodsuger.uiElement.screens.activities.BloodSugarHistoryScreen
import com.example.heartrate.uiElement.screens.activities.HeartRateHistoryScreen
import com.example.bloodpressure.uiElement.screens.activities.BloodPressureHistoryScreen
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.items.DropDownMenuPagerSection
import com.example.sharedui.uiElement.containers.pager.data.MenuData
import com.example.sharedui.uiElement.containers.pager.scrollToPage
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
internal fun HistoryScreen(
    popHistoryDestination: () -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0)

    val coroutineScope = rememberCoroutineScope()

    HistoryContent(
        onClickBack = popHistoryDestination,
        pagerState = pagerState,
        healthCareMenusData = arrayOf(
            MenuData(
                title = stringResource(
                    R.string.bmi
                ),
                onClick = {
                    //execute scroll here
                    pagerState.scrollToPage(
                        coroutineScope = coroutineScope,
                        page = 0
                    )

                }
            ),
            MenuData(
                title = stringResource(
                    R.string.blood_pressure
                ),
                onClick = {
                    //execute scroll here
                    pagerState.scrollToPage(
                        coroutineScope = coroutineScope,
                        page = 1
                    )

                }
            ),
            MenuData(
                title = stringResource(
                    R.string.blood_suger
                ),
                onClick = {
                    //execute scroll here
                    pagerState.scrollToPage(
                        coroutineScope = coroutineScope,
                        page = 2
                    )

                }
            ),
            MenuData(
                title = stringResource(
                    R.string.heart_rate
                ),
                onClick = {
                    //execute scroll here
                    pagerState.scrollToPage(
                        coroutineScope = coroutineScope,
                        page = 3
                    )

                }
            ),
        )
    )
}//end HistoryScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HistoryContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickBack: () -> Unit,
    pagerState: PagerState,
    healthCareMenusData: Array<MenuData>
) {

    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        ConstraintLayout(
            modifier = Modifier
                .safeDrawingPadding()
                .fillMaxSize()
                .background(
                    color = theme.background
                )
        ) {
            val (backButtonId, titleId, dropDownMenuId, historyPageId) = createRefs()
            val guideFromStart156DP = createGuidelineFromStart(dimen.dimen_21_5.dp)

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
                            dimen.dimen_2_5.dp
                        )
                    }//end constrainAs
            )

            TextBoldView(
                theme = theme,
                dimen = dimen,
                text = stringResource(
                    com.example.sharedui.R.string.all_history
                ),
                size = dimen.dimen_2_5,
                color = theme.black,
                modifier = Modifier
                    .constrainAs(titleId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(
                            parent.top,
                            dimen.dimen_2_5.dp
                        )
                    }
            )

            DropDownMenuPagerSection(
                dimen = dimen,
                theme = theme,
                menus = healthCareMenusData,
                textSelectedSize = dimen.dimen_2,
                textItemSize = dimen.dimen_1_75,
                dropDownMenuWidth = dimen.dimen_19_5,
                modifier = Modifier
                    .constrainAs(dropDownMenuId) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_1.dp
                        )
                        end.linkTo(guideFromStart156DP)
                        top.linkTo(
                            backButtonId.bottom,
                            dimen.dimen_4.dp
                        )
                        width = Dimension.fillToConstraints
                    }
            )

            HorizontalPager(
                state = pagerState,
                pageCount = 4,
                userScrollEnabled = false,
                modifier = Modifier
                    .constrainAs(historyPageId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(
                            parent.top,
                            dimen.dimen_13_5.dp
                        )
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }
            ) { page ->

                when (page) {
                    0 -> {

                        BMIHistoryScreen(
                            theme = theme,
                            dimen = dimen
                        )
                    }

                    1 -> {

                        BloodPressureHistoryScreen(
                            theme = theme,
                            dimen = dimen
                        )
                    }

                    2 -> {

                        BloodSugarHistoryScreen(
                            theme = theme,
                            dimen = dimen
                        )
                    }

                    3 -> {

                        HeartRateHistoryScreen(
                            theme = theme,
                            dimen = dimen
                        )
                    }
                }//end when

            }//end HorizontalPager

        }//end ConstraintLayout

    }//end BaseScreen

}//end HistoryContent