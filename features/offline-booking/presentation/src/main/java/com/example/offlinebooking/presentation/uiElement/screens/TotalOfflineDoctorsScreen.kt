package com.example.offlinebooking.presentation.uiElement.screens

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
import com.example.offline.booking.domain.model.OfflineDoctorModel
import com.example.offlinebooking.presentation.uiElement.components.items.OfflineDoctorSection
import com.example.offlinebooking.presentation.uiState.state.TotalOfflineDoctorsUiState
import com.example.offlinebooking.presentation.uiState.viewModel.TotalOfflineDoctorsViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun TotalOfflineDoctorsScreen(
    viewModel: TotalOfflineDoctorsViewModel = hiltViewModel(),
    theme: CustomTheme,
    dimen: CustomDimen,
    navigateToBookingNavGraph: (Int) -> Unit
) {
    //get screen state here
    val state = viewModel.state.collectAsState()

    //create total offline doctors content here
    TotalOfflineDoctorsContent(
        theme = theme,
        dimen = dimen,
        navigateToBookingNavGraph = navigateToBookingNavGraph,
        uiState = state.value,
        totalOfflineDoctorsStatus = state.value.totalOfflineDoctorsStatus?.collectAsLazyPagingItems(),
    )
}//end TotalOfflineDoctorsScreen

@Composable
private fun TotalOfflineDoctorsContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    navigateToBookingNavGraph: (Int) -> Unit,
    uiState: TotalOfflineDoctorsUiState,
    totalOfflineDoctorsStatus: LazyPagingItems<OfflineDoctorModel>?,
) {

    AnimatedVisibility(
        visible = totalOfflineDoctorsStatus?.loadState?.refresh is LoadState.Loading,
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
                OfflineDoctorSection(
                    dimen = dimen,
                    theme = theme,
                    textButton = stringResource(
                        id = R.string.book_now
                    ),
                    onClickOnButton = {},
                    doctorIsOnline = false,
                    offlineDoctor = uiState.doctorPlaceHolder,
                    placeHolderState = true,
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end items

        }//end LazyColumn

    }//end AnimatedVisibility


    AnimatedVisibility(
        visible = totalOfflineDoctorsStatus?.loadState?.refresh is LoadState.NotLoading,
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
            totalOfflineDoctorsStatus?.itemCount?.let {
                items(
                    count = it
                ) { count ->

                    //create single doctor here
                    totalOfflineDoctorsStatus[count]?.let { offlineDoctor ->
                        OfflineDoctorSection(
                            dimen = dimen,
                            theme = theme,
                            textButton = stringResource(
                                id = R.string.book_now
                            ),
                            onClickOnButton = navigateToBookingNavGraph,
                            doctorIsOnline = false,
                            offlineDoctor = offlineDoctor,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                }

            }//end items

        }//end LazyColumn

    }//end AnimatedVisibility

}//end TotalOfflineDoctorsContent