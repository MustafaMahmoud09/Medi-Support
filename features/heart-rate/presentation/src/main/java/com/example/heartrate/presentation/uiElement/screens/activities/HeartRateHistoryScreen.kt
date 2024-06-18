@file:OptIn(ExperimentalMaterialApi::class)

package com.example.heartrate.presentation.uiElement.screens.activities

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.heart.rate.domain.domain.model.SimpleHeartRateModel
import com.example.heartrate.presentation.uiElement.components.items.SingleHistorySection
import com.example.heartrate.presentation.uiState.state.HeartRateHistoryUiState
import com.example.heartrate.presentation.uiState.viewModel.HeartRateHistoryViewModel
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import kotlin.reflect.KFunction0

@Composable
fun HeartRateHistoryScreen(
    viewModel: HeartRateHistoryViewModel = hiltViewModel(),
    theme: CustomTheme,
    dimen: CustomDimen,
) {
    //get screen state here
    val state = viewModel.state.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.value.refreshState,
        onRefresh = viewModel::onRefreshHeartRateRecords
    )

    HeartRateHistoryContent(
        theme = theme,
        dimen = dimen,
        uiState = state.value,
        heartRateRecords = state.value.heartRateRecords?.collectAsLazyPagingItems(),
        pullRefreshState = pullRefreshState,
        heartRateRecordsBackup = state.value.heartRateRecordsBackup?.collectAsLazyPagingItems(),
        onHeartRateBackupCreated = viewModel::onHeartRateBackupCreated
    )
}//end HeartRateHistoryScreen

@Composable
private fun HeartRateHistoryContent(
    theme: CustomTheme,
    dimen: CustomDimen,
    uiState: HeartRateHistoryUiState,
    heartRateRecords: LazyPagingItems<SimpleHeartRateModel>?,
    pullRefreshState: PullRefreshState,
    heartRateRecordsBackup: LazyPagingItems<SimpleHeartRateModel>?,
    onHeartRateBackupCreated: KFunction0<Unit>,
) {

    //create container here
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = dimen.dimen_0_75.dp,
                bottom = dimen.dimen_1.dp,
                start = dimen.dimen_1.dp,
                end = dimen.dimen_1.dp
            )
            .background(
                color = theme.background
            )
            .appBorder(
                shape = RoundedCornerShape(
                    size = dimen.dimen_0_75.dp
                ),
                borderWidth = dimen.dimen_0_125,
                borderColor = theme.redDark
            ),
    ) {
        //create ids for components here
        val (histories, historiesPlaceHolder) = createRefs()


        //if request state is loading show placeholder
        AnimatedVisibility(
            visible = heartRateRecords?.loadState?.refresh !is LoadState.NotLoading &&
                    heartRateRecordsBackup?.loadState?.refresh !is LoadState.NotLoading,
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
                .constrainAs(historiesPlaceHolder) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
        ) {

            //create column contain on all histories here
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(
                    horizontal = dimen.dimen_1.dp,
                    vertical = dimen.dimen_2.dp
                ),
                verticalArrangement = Arrangement.spacedBy(
                    space = dimen.dimen_2.dp
                )
            ) {

                //create history items here
                items(
                    count = 15
                ) {

                    //create single history here
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = dimen.dimen_1_5.dp
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        SingleHistorySection(
                            dimen = dimen,
                            theme = theme,
                            record = uiState.placeHolderHeartRate,
                            placeHolderState = true,
                            modifier = Modifier
                                .fillMaxWidth(),
                        )

                    }//end Box

                }//end items

            }//end LazyColumn

        }//end LazyColumn


        //if request state is success show items
        AnimatedVisibility(
            visible = heartRateRecords?.loadState?.refresh is LoadState.NotLoading ||
                    heartRateRecordsBackup?.loadState?.refresh is LoadState.NotLoading,
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
                .constrainAs(histories) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
        ) {

            val heartRates = if (heartRateRecords?.loadState?.refresh is LoadState.NotLoading) {
                heartRateRecords
            }//end if
            else {
                heartRateRecordsBackup
            }//end else

            Box(
                modifier = Modifier
                    .pullRefresh(pullRefreshState)
            ) {

                //create column contain on all histories here
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(
                        horizontal = dimen.dimen_1.dp,
                        vertical = dimen.dimen_2.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(
                        space = dimen.dimen_2.dp
                    )
                ) {

                    //create history items here
                    heartRates?.let {
                        items(
                            count = it.itemCount
                        ) { count ->

                            //create single history here
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = dimen.dimen_1_5.dp
                                    ),
                                contentAlignment = Alignment.Center
                            ) {

                                SingleHistorySection(
                                    dimen = dimen,
                                    theme = theme,
                                    record = heartRates[count]!!,
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                )

                            }//end Box

                        }
                    }//end items

                }//end LazyColumn

                PullRefreshIndicator(
                    refreshing = uiState.refreshState,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )

            }//end Box

        }//end LazyColumn

    }//end ConstraintLayout

    LaunchedEffect(
        key1 = heartRateRecords?.loadState?.refresh
    ) {

        if (heartRateRecords?.loadState?.refresh is LoadState.NotLoading) {

            onHeartRateBackupCreated()

        }//end if

    }//end LaunchedEffect

}//end HeartRateHistoryContent