@file:OptIn(ExperimentalMaterialApi::class)

package com.example.bloodsugar.presentation.uiElement.screens.activities


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.example.bloodsugar.presentation.uiElement.components.items.SomeHistorySection
import com.example.bloodsugar.presentation.uiState.state.BloodSugarActivityUiState
import com.example.bloodsugar.presentation.uiState.viewModel.BloodSugarActivityViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.ColumnChartView
import com.example.sharedui.uiElement.components.items.DaySection
import com.example.sharedui.uiElement.components.items.RecommendedSection
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun BloodSugarActivityScreen(
    viewModel: BloodSugarActivityViewModel = hiltViewModel(),
    theme: CustomTheme,
    dimen: CustomDimen,
    navigateToHistoryDestination: () -> Unit
) {

    //get screen state here
    val state = viewModel.state.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.value.refreshState,
        onRefresh = viewModel::onRefreshBloodSugarActivity
    )

    BloodSugarActivityContent(
        theme = theme,
        dimen = dimen,
        onClickSeeAll = navigateToHistoryDestination,
        uiState = state.value,
        pullRefreshState = pullRefreshState,
        daysColumnState = rememberLazyListState(),
    )
}//end BloodSugarScreen


@Composable
private fun BloodSugarActivityContent(
    theme: CustomTheme,
    dimen: CustomDimen,
    onClickSeeAll: () -> Unit,
    uiState: BloodSugarActivityUiState,
    daysColumnState: LazyListState,
    pullRefreshState: PullRefreshState
) {

    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = theme.background
                ),
            contentPadding = PaddingValues(
                vertical = dimen.dimen_2.dp
            )
        ) {


            //create item contain on all screen components
            item(
                key = 1
            ) {

                //create container here
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    //create ids for screen components here
                    val (daysId, chartId, someHistoryId, recommendedId) = createRefs()

                    //create row contain on days here
                    LazyRow(
                        state = daysColumnState,
                        contentPadding = PaddingValues(
                            horizontal = dimen.dimen_1_5.dp
                        ),
                        horizontalArrangement = Arrangement.spacedBy(
                            space = dimen.dimen_1_5.dp,
                        ),
                        modifier = Modifier
                            .constrainAs(daysId) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                width = Dimension.fillToConstraints
                            }
                    ) {

                        //create days here
                        items(
                            count = uiState.monthDays.size
                        ) { count ->

                            //create single day here
                            DaySection(
                                dimen = dimen,
                                theme = theme,
                                dayName = uiState.monthDays[count].name,
                                dayNumber = "${uiState.monthDays[count].number}",
                                toDay = uiState.monthDays[count].today
                            )

                        }//end items

                    }//end LazyRow

                    //create blood sugar chart here
                    ColumnChartView(
                        theme = theme,
                        dimen = dimen,
                        data = uiState.getBloodSugarChartStatus.dataResult,
                        maxValue = uiState.getBloodSugarChartStatus.maxValue.toFloat(),
                        xAxisData = uiState.getBloodSugarChartStatus.xAxisData.ifEmpty {
                            listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
                        },
                        modifier = Modifier
                            .constrainAs(chartId) {
                                start.linkTo(
                                    parent.start,
                                    dimen.dimen_3_5.dp
                                )
                                end.linkTo(
                                    parent.end,
                                    dimen.dimen_3_5.dp
                                )
                                top.linkTo(
                                    daysId.bottom,
                                    dimen.dimen_5_5.dp
                                )
                                width = Dimension.fillToConstraints
                            }
                            .aspectRatio(1.42f)
                    )

                    //create some history section here
                    SomeHistorySection(
                        dimen = dimen,
                        theme = theme,
                        onClickSeeAll = onClickSeeAll,
                        historyRecords = uiState.lastHistoryBloodSugarRecords,
                        modifier = Modifier
                            .constrainAs(someHistoryId) {
                                top.linkTo(
                                    chartId.bottom,
                                    dimen.dimen_5_5.dp
                                )
                                start.linkTo(
                                    parent.start,
                                    dimen.dimen_2.dp
                                )
                                end.linkTo(
                                    parent.end,
                                    dimen.dimen_2.dp
                                )
                                width = Dimension.fillToConstraints
                            }
                    )

                    //create recommended section here
                    RecommendedSection(
                        dimen = dimen,
                        theme = theme,
                        equationText = stringResource(R.string.how_to_control_blood_sugar),
                        responseText = uiState.adviceBloodSugarModel?.advice ?: "",
                        modifier = Modifier
                            .constrainAs(recommendedId) {
                                start.linkTo(
                                    parent.start,
                                    dimen.dimen_2.dp
                                )
                                end.linkTo(
                                    parent.end,
                                    dimen.dimen_2.dp
                                )
                                top.linkTo(
                                    someHistoryId.bottom,
                                    dimen.dimen_1_75.dp
                                )
                                width = Dimension.fillToConstraints
                            }
//                            .placeholder(
//                            visible = uiState.adviceBloodSugarModel == null,
//                            color = theme.background
//                        )
                    )

                }//end ConstraintLayout

            }//end item

        }//end LazyColumn

        PullRefreshIndicator(
            refreshing = uiState.refreshState,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )

    }//end Box


    LaunchedEffect(key1 = true) {

        daysColumnState.scrollToItem(uiState.currentDayNumber - 1)

    }//end LaunchedEffect

}//end BloodSugarContent