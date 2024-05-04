package com.example.onlinebooking.presentation.uiElement.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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

@Composable
fun TotalOnlineDoctorsScreen(
    viewModel: TotalOnlineDoctorsViewModel = hiltViewModel(),
    theme: CustomTheme,
    dimen: CustomDimen,
    navigateToBookingNavGraph: (Int) -> Unit
) {
    //get screen state here
    val state = viewModel.state.collectAsState()

    //create total online doctors content here
    TotalOnlineDoctorsContent(
        theme = theme,
        dimen = dimen,
        navigateToBookingNavGraph = navigateToBookingNavGraph,
        uiState = state.value,
        totalOnlineDoctorsStatus = state.value.totalOnlineDoctorsStatus?.collectAsLazyPagingItems()
    )
}//end TotalOnlineDoctorsScreen

@Composable
private fun TotalOnlineDoctorsContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    navigateToBookingNavGraph: (Int) -> Unit,
    uiState: TotalOnlineDoctorsUiState,
    totalOnlineDoctorsStatus: LazyPagingItems<OnlineDoctorModel>?,
) {

    AnimatedVisibility(
        visible = totalOnlineDoctorsStatus?.loadState?.refresh is LoadState.Loading,
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
        visible = totalOnlineDoctorsStatus?.loadState?.refresh is LoadState.NotLoading,
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
            totalOnlineDoctorsStatus?.itemCount?.let {
                items(
                    count = it
                ) { count ->

                    //create single doctor here
                    totalOnlineDoctorsStatus[count]?.let { offlineDoctor ->
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

    }//end AnimatedVisibility


}//end TotalOnlineDoctorsContent