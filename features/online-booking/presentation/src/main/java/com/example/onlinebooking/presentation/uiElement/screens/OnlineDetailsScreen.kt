@file:OptIn(ExperimentalMaterialApi::class)

package com.example.onlinebooking.presentation.uiElement.screens

import android.annotation.SuppressLint
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import com.example.sharedui.R
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.rememberPaymentSheet
import kotlinx.coroutines.delay
import kotlin.reflect.KFunction0

@Composable
fun OnlineDetailsScreen(
    viewModel: OnlineDetailsViewModel = hiltViewModel(),
    dimen: CustomDimen,
    theme: CustomTheme,
    navigateToOnlineRoomNavGraph: (Int) -> Unit
) {
    //get screen state here
    val state = viewModel.state.collectAsState()

    val paymentSheet = rememberPaymentSheet(
        viewModel::handlePaymentResult
    )

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.value.refreshState,
        onRefresh = viewModel::onRefreshOnlineBooking
    )

    val snackbarHostState = remember { SnackbarHostState() }

    val internetError =
        stringResource(R.string.the_device_is_not_connected_to_the_internet)

    val serverError =
        stringResource(R.string.there_is_a_problem_with_the_server)

    val startRunning = remember {
        mutableStateOf(true)
    }

    //create online details content here
    OnlineDetailsContent(
        dimen = dimen,
        theme = theme,
        pullRefreshState = pullRefreshState,
        onClickOnVideoCallButton = { id ->
            if (!state.value.getPaymentIntentSecretStatus.loading) {
                viewModel.onGetPaymentIntentSecret(id)
            }//end if
        },
        onOnlineBookingBackupCreated = viewModel::onOnlineBookingBackupCreated,
        uiState = state.value,
        totalOnlineBookingStatus = state.value.totalOnlineBookingStatus?.collectAsLazyPagingItems(),
        cacheTotalOnlineBookingStatus = state.value.cacheTotalOnlineBookingStatus?.collectAsLazyPagingItems(),
        snackbarHostState = snackbarHostState
    )

    LaunchedEffect(
        key1 = state.value.getPaymentIntentSecretStatus.success
    ) {

        if (!startRunning.value) {

            if (state.value.getPaymentIntentSecretStatus.success) {

                paymentSheet.presentWithPaymentIntent(
                    paymentIntentClientSecret = state.value.getPaymentIntentSecretStatus.paymentModel.paymentIntent,
                    configuration = PaymentSheet.Configuration.Builder(
                        merchantDisplayName = "Online Booking"
                    ).build()
                )

            }//end if

        }//end if

    }//end LaunchedEffect


    LaunchedEffect(
        key1 = state.value.getPaymentIntentSecretStatus.paymentDone
    ) {

        if (!startRunning.value) {

            if (state.value.getPaymentIntentSecretStatus.paymentDone) {
                navigateToOnlineRoomNavGraph(state.value.getPaymentIntentSecretStatus.bookingId.toInt())
            }//end if

        }//end if

    }//end LaunchedEffect


    LaunchedEffect(
        key1 = state.value.getPaymentIntentSecretStatus.internetError
    ) {

        if (!startRunning.value) {

            snackbarHostState.showSnackbar(
                message = internetError
            )
        }//end if

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = state.value.getPaymentIntentSecretStatus.serverError
    ) {

        if (!startRunning.value) {

            snackbarHostState.showSnackbar(
                message = serverError
            )
        }//end if

    }//end LaunchedEffect

    LaunchedEffect(key1 = true) {
        delay(250)
        startRunning.value = false
    }

}//end OnlineDetailsScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun OnlineDetailsContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickOnVideoCallButton: (Long) -> Unit,
    uiState: OnlineDetailsUiState,
    totalOnlineBookingStatus: LazyPagingItems<OnlineBookingModel>?,
    cacheTotalOnlineBookingStatus: LazyPagingItems<OnlineBookingModel>?,
    snackbarHostState: SnackbarHostState,
    onOnlineBookingBackupCreated: KFunction0<Unit>,
    pullRefreshState: PullRefreshState
) {

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }//end snack bar Host
    ) {

        AnimatedVisibility(
            visible = totalOnlineBookingStatus?.loadState?.refresh !is LoadState.NotLoading &&
                    cacheTotalOnlineBookingStatus?.loadState?.refresh !is LoadState.NotLoading,
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
                        onClickOnVideoCallButton = { },
                        message = stringResource(
                            R.string.now_you_can_make_video_call_with_the_doctor
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
            visible = totalOnlineBookingStatus?.loadState?.refresh is LoadState.NotLoading ||
                    cacheTotalOnlineBookingStatus?.loadState?.refresh is LoadState.NotLoading,
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

            val bookings =
                if (totalOnlineBookingStatus?.loadState?.refresh is LoadState.NotLoading) {
                    totalOnlineBookingStatus
                }//end if
                else {
                    cacheTotalOnlineBookingStatus
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
                        all = dimen.dimen_2.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(
                        space = dimen.dimen_1_5.dp
                    )
                ) {

                    //create online booking items here
                    bookings?.let {
                        items(
                            count = it.itemCount
                        ) { count ->

                            //create online booking here
                            bookings[count]?.let { booking ->
                                OnlineBookingSection(
                                    dimen = dimen,
                                    theme = theme,
                                    onClickOnVideoCallButton = onClickOnVideoCallButton,
                                    message = stringResource(
                                        R.string.now_you_can_make_video_call_with_the_doctor
                                    ),
                                    onlineBooking = booking,
                                    itemIdLoad = uiState.getPaymentIntentSecretStatus.bookingId,
                                    loadState = uiState.getPaymentIntentSecretStatus.loading,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }//end item

                        }//end items

                    }//end items

                }//end LazyColumn

                PullRefreshIndicator(
                    refreshing = uiState.refreshState,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter),
                )

            }//end Box

        }//end AnimatedVisibility

    }//end Scaffold

    LaunchedEffect(
        key1 = totalOnlineBookingStatus?.loadState?.refresh
    ) {

        if (totalOnlineBookingStatus?.loadState?.refresh is LoadState.NotLoading) {

            onOnlineBookingBackupCreated()

        }//end if

    }//end LaunchedEffect

}//end OnlineDetailsContent