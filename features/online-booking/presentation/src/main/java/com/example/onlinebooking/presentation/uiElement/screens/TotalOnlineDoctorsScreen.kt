@file:OptIn(ExperimentalMaterialApi::class)

package com.example.onlinebooking.presentation.uiElement.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.online.booking.domain.model.OnlineDoctorModel
import com.example.onlinebooking.presentation.uiElement.components.items.OnlineDoctorSection
import com.example.onlinebooking.presentation.uiState.state.TotalOnlineDoctorsUiState
import com.example.onlinebooking.presentation.uiState.viewModel.TotalOnlineDoctorsViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import kotlin.reflect.KFunction0

@Composable
fun TotalOnlineDoctorsScreen(
    viewModel: TotalOnlineDoctorsViewModel = hiltViewModel(),
    theme: CustomTheme,
    dimen: CustomDimen,
    navigateToBookingNavGraph: (Int) -> Unit
) {
    //get screen state here
    val state = viewModel.state.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.value.refreshState,
        onRefresh = viewModel::onRefreshOnlineDoctors
    )

    //create total online doctors content here
    TotalOnlineDoctorsContent(
        theme = theme,
        dimen = dimen,
        pullRefreshState = pullRefreshState,
        navigateToBookingNavGraph = navigateToBookingNavGraph,
        uiState = state.value,
        onTotalOnlineDoctorsBackupCreated = viewModel::onTotalOnlineDoctorsBackupCreated,
        totalOnlineDoctorsStatus = state.value.totalOnlineDoctorsStatus?.collectAsLazyPagingItems(),
        cacheOnlineDoctorsStatus = state.value.cacheOnlineDoctorsStatus?.collectAsLazyPagingItems()
    )
}//end TotalOnlineDoctorsScreen

@Composable
private fun TotalOnlineDoctorsContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    navigateToBookingNavGraph: (Int) -> Unit,
    uiState: TotalOnlineDoctorsUiState,
    totalOnlineDoctorsStatus: LazyPagingItems<OnlineDoctorModel>?,
    pullRefreshState: PullRefreshState,
    cacheOnlineDoctorsStatus: LazyPagingItems<OnlineDoctorModel>?,
    onTotalOnlineDoctorsBackupCreated: KFunction0<Unit>,
) {

    AnimatedVisibility(
        visible = totalOnlineDoctorsStatus?.loadState?.refresh !is LoadState.NotLoading &&
                cacheOnlineDoctorsStatus?.loadState?.refresh !is LoadState.NotLoading,
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
            .fillMaxSize()
    ) {

        //create container here
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(
                bottom = dimen.dimen_2.dp,
                top = dimen.dimen_1_5.dp,
                start = dimen.dimen_2.dp,
                end = dimen.dimen_2.dp
            ),
            verticalArrangement = Arrangement.spacedBy(
                space = dimen.dimen_1_5.dp
            ),
        ) {

            //create doctor items
            items(
                count = 10
            ) {

                //create single doctor here
                OnlineDoctorSection(
                    dimen = dimen,
                    theme = theme,
                    textButton = stringResource(
                        id = R.string.book_now
                    ),
                    onClickOnButton = {},
                    onlineDoctor = uiState.doctorPlaceHolder,
                    placeHolderState = true,
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end items

        }//end LazyColumn

    }//end AnimatedVisibility


    AnimatedVisibility(
        visible = totalOnlineDoctorsStatus?.loadState?.refresh is LoadState.NotLoading ||
                cacheOnlineDoctorsStatus?.loadState?.refresh is LoadState.NotLoading,
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
            .fillMaxSize()
    ) {

        val onlineDoctors =  if (totalOnlineDoctorsStatus?.loadState?.refresh is LoadState.NotLoading) {
            totalOnlineDoctorsStatus
        }//end if
        else {
            cacheOnlineDoctorsStatus
        }//end else

        Box(
            modifier = Modifier
                .pullRefresh(
                    state = pullRefreshState
                )
        ) {

            //create container here
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(
                    bottom = dimen.dimen_2.dp,
                    top = dimen.dimen_1_5.dp,
                    start = dimen.dimen_2.dp,
                    end = dimen.dimen_2.dp
                ),
                verticalArrangement = Arrangement.spacedBy(
                    space = dimen.dimen_1_5.dp
                ),
            ) {

                //create doctor items
                onlineDoctors?.itemCount?.let {
                    items(
                        count = it
                    ) { count ->

                        //create single doctor here
                        onlineDoctors[count]?.let { offlineDoctor ->
                            OnlineDoctorSection(
                                dimen = dimen,
                                theme = theme,
                                textButton = stringResource(
                                    id = R.string.book_now
                                ),
                                onClickOnButton = navigateToBookingNavGraph,
                                onlineDoctor = offlineDoctor,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }

                    }

                }//end items

            }//end LazyColumn

            PullRefreshIndicator(
                refreshing = uiState.refreshState,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )

        }//end Box

    }//end AnimatedVisibility


    LaunchedEffect(
        key1 = totalOnlineDoctorsStatus?.loadState?.refresh
    ) {

        if (totalOnlineDoctorsStatus?.loadState?.refresh is LoadState.NotLoading) {

            onTotalOnlineDoctorsBackupCreated()

        }//end if

    }//end LaunchedEffect

}//end TotalOnlineDoctorsContent