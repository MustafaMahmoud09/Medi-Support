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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onlinebooking.presentation.uiElement.components.items.OnlineDoctorSection
import com.example.onlinebooking.presentation.uiState.state.TopOnlineDoctorsUiState
import com.example.onlinebooking.presentation.uiState.viewModel.TopOnlineDoctorsViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun TopOnlineDoctorsScreen(
    viewModel: TopOnlineDoctorsViewModel = hiltViewModel(),
    theme: CustomTheme,
    dimen: CustomDimen,
    navigateToBookingNavGraph: (Int) -> Unit
) {
    //get screen state here
    val state = viewModel.state.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.value.refreshState,
        onRefresh = viewModel::onRefreshTopOnlineDoctors
    )

    //create top online doctors content here
    TopOnlineDoctorsContent(
        theme = theme,
        dimen = dimen,
        navigateToBookingNavGraph = navigateToBookingNavGraph,
        uiState = state.value,
        pullRefreshState = pullRefreshState
    )
}//end TopOnlineDoctorsScreen

@Composable
private fun TopOnlineDoctorsContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    navigateToBookingNavGraph: (Int) -> Unit,
    uiState: TopOnlineDoctorsUiState,
    pullRefreshState: PullRefreshState,
) {

    AnimatedVisibility(
        visible = uiState.numberOfSuccessRequest == 0,
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
        visible = uiState.numberOfSuccessRequest > 0,
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
                items(
                    count = uiState.getTopOnlineDoctorsStatus.data!!.size
                ) { count ->

                    //create single doctor here
                    OnlineDoctorSection(
                        dimen = dimen,
                        theme = theme,
                        textButton = stringResource(
                            id = R.string.book_now
                        ),
                        onClickOnButton = navigateToBookingNavGraph,
                        onlineDoctor = uiState.getTopOnlineDoctorsStatus.data[count],
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                }//end items

            }//end LazyColumn

            PullRefreshIndicator(
                refreshing = uiState.refreshState,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )

        }//end Box

    }//end AnimatedVisibility

}//end TopOnlineDoctorsScreen