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
import com.example.online.booking.domain.model.OnlineBookingModel
import com.example.onlinebooking.presentation.uiElement.components.items.OnlineBookingSection
import com.example.onlinebooking.presentation.uiState.state.OnlineDetailsUiState
import com.example.onlinebooking.presentation.uiState.viewModel.OnlineDetailsViewModel
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun OnlineDetailsScreen(
    viewModel: OnlineDetailsViewModel = hiltViewModel(),
    dimen: CustomDimen,
    theme: CustomTheme,
    navigateToOnlineRoomNavGraph: () -> Unit
) {
    //get screen state here
    val state = viewModel.state.collectAsState()

    //create online details content here
    OnlineDetailsContent(
        dimen = dimen,
        theme = theme,
        onClickOnVideoCallButton = navigateToOnlineRoomNavGraph,
        uiState = state.value,
        totalOnlineBookingStatus = state.value.totalOnlineBookingStatus?.collectAsLazyPagingItems()
    )

}//end OnlineDetailsScreen

@Composable
private fun OnlineDetailsContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickOnVideoCallButton: () -> Unit,
    uiState: OnlineDetailsUiState,
    totalOnlineBookingStatus: LazyPagingItems<OnlineBookingModel>?
) {

    AnimatedVisibility(
        visible = totalOnlineBookingStatus?.loadState?.refresh is LoadState.Loading,
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
                all = dimen.dimen_2.dp
            ),
            verticalArrangement = Arrangement.spacedBy(
                space = dimen.dimen_1_5.dp
            )
        ) {

            //create online booking items here
            items(
                count = 10
            ) {

                //create online booking here
                OnlineBookingSection(
                    dimen = dimen,
                    theme = theme,
                    onClickOnVideoCallButton = {},
                    message = stringResource(
                        com.example.sharedui.R.string.now_you_can_make_video_call_with_the_doctor
                    ),
                    onlineBooking = uiState.bookingPlaceHolder,
                    placeHolderState = true,
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end items

        }//end LazyColumn

    }//end AnimatedVisibility


    AnimatedVisibility(
        visible = totalOnlineBookingStatus?.loadState?.refresh is LoadState.NotLoading,
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
                all = dimen.dimen_2.dp
            ),
            verticalArrangement = Arrangement.spacedBy(
                space = dimen.dimen_1_5.dp
            )
        ) {

            //create online booking items here
            totalOnlineBookingStatus?.let {
                items(
                    count = it.itemCount
                ) { count ->

                    //create online booking here
                    totalOnlineBookingStatus[count]?.let { booking ->
                        OnlineBookingSection(
                            dimen = dimen,
                            theme = theme,
                            onClickOnVideoCallButton = onClickOnVideoCallButton,
                            message = stringResource(
                                com.example.sharedui.R.string.now_you_can_make_video_call_with_the_doctor
                            ),
                            onlineBooking = booking,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                }

            }//end items

        }//end LazyColumn

    }//end AnimatedVisibility

}//end OnlineDetailsContent