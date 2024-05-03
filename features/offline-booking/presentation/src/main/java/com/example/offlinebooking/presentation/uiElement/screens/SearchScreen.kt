package com.example.offlinebooking.presentation.uiElement.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.offlinebooking.presentation.uiState.state.SearchUiState
import com.example.offlinebooking.presentation.uiState.viewModel.SearchViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

//function for collect state and execute action from view model
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    dimen: CustomDimen,
    theme: CustomTheme,
    navigateToBookingNavGraph: (Int) -> Unit
) {
    //get screen state here
    val state = viewModel.state.collectAsState()

    //call search content function
    SearchContent(
        dimen = dimen,
        theme = theme,
        onClickOnBookingButton = navigateToBookingNavGraph,
        uiState = state.value,
        searchOfflineDoctorsStatus = state.value.searchOfflineDoctorsStatus?.collectAsLazyPagingItems()
    )
}//end SearchContent

//function for observe state and draw components
@Composable
private fun SearchContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickOnBookingButton: (Int) -> Unit,
    uiState: SearchUiState,
    searchOfflineDoctorsStatus: LazyPagingItems<OfflineDoctorModel>?,
) {


    AnimatedVisibility(
        visible = searchOfflineDoctorsStatus?.loadState?.refresh is LoadState.Loading,
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
                .fillMaxSize()
                .background(
                    color = theme.background
                )
                .padding(
                    top = dimen.dimen_1.dp
                ),
            contentPadding = PaddingValues(
                start = dimen.dimen_2.dp,
                end = dimen.dimen_2.dp,
                bottom = dimen.dimen_2.dp,
                top = dimen.dimen_1.dp
            ),
            verticalArrangement = Arrangement.spacedBy(
                space = dimen.dimen_1_5.dp
            )
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
        visible = searchOfflineDoctorsStatus?.loadState?.refresh is LoadState.NotLoading,
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
                .fillMaxSize()
                .background(
                    color = theme.background
                )
                .padding(
                    top = dimen.dimen_1.dp
                ),
            contentPadding = PaddingValues(
                start = dimen.dimen_2.dp,
                end = dimen.dimen_2.dp,
                bottom = dimen.dimen_2.dp,
                top = dimen.dimen_1.dp
            ),
            verticalArrangement = Arrangement.spacedBy(
                space = dimen.dimen_1_5.dp
            )
        ) {

            //create doctor items
            searchOfflineDoctorsStatus?.itemCount?.let {
                items(
                    count = it
                ) { count ->

                    //create single doctor here
                    searchOfflineDoctorsStatus[count]?.let { offlineDoctor ->
                        OfflineDoctorSection(
                            dimen = dimen,
                            theme = theme,
                            textButton = stringResource(
                                id = R.string.book_now
                            ),
                            onClickOnButton = onClickOnBookingButton,
                            doctorIsOnline = false,
                            offlineDoctor = offlineDoctor,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }//end item

                }//end items

            }//end items

        }//end LazyColumn

    }//end AnimatedVisibility

}//end SearchContent