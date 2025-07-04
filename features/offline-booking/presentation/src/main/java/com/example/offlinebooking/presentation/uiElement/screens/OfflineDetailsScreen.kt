@file:OptIn(ExperimentalMaterialApi::class)

package com.example.offlinebooking.presentation.uiElement.screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.offline.booking.domain.model.OfflineBookingModel
import com.example.offlinebooking.presentation.uiElement.components.items.OfflineBookingSection
import com.example.offlinebooking.presentation.uiState.state.OfflineDetailsUiState
import com.example.offlinebooking.presentation.uiState.viewModel.OfflineDetailsViewModel
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import kotlin.reflect.KFunction0

@Composable
fun OfflineDetailsScreen(
    viewModel: OfflineDetailsViewModel = hiltViewModel(),
    dimen: CustomDimen,
    theme: CustomTheme,
    navigateToChatNavGraph: () -> Unit,
    navigateToChatDestination: (Int) -> Unit
) {
    //get screen state here
    val state = viewModel.state.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.value.refreshState,
        onRefresh = viewModel::onRefreshTotalOfflineBookings
    )

    //create offline details content here
    OfflineDetailsContent(
        dimen = dimen,
        theme = theme,
        onClickOnChatButton = navigateToChatNavGraph,
        onClickOnChatNowButton = navigateToChatDestination,
        uiState = state.value,
        totalOfflineBookingStatus = state.value.totalOfflineBookingStatus?.collectAsLazyPagingItems(),
        cacheOfflineBookingsStatus = state.value.cacheTotalOfflineBookingStatus?.collectAsLazyPagingItems(),
        pullRefreshState = pullRefreshState,
        onTotalOfflineBookingsBackupCreated = viewModel::onTotalOfflineBookingsBackupCreated
    )

}//end OfflineDetailsScreen

@Composable
private fun OfflineDetailsContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickOnChatButton: () -> Unit,
    uiState: OfflineDetailsUiState,
    totalOfflineBookingStatus: LazyPagingItems<OfflineBookingModel>?,
    pullRefreshState: PullRefreshState,
    cacheOfflineBookingsStatus: LazyPagingItems<OfflineBookingModel>?,
    onTotalOfflineBookingsBackupCreated: KFunction0<Unit>,
    onClickOnChatNowButton: (Int) -> Unit
) {


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        //create ids for screen components here
        val (chatFabId) = createRefs()

        AnimatedVisibility(
            visible = totalOfflineBookingStatus?.loadState?.refresh !is LoadState.NotLoading &&
                    cacheOfflineBookingsStatus?.loadState?.refresh !is LoadState.NotLoading,
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

                    //create offline booking here
                    OfflineBookingSection(
                        dimen = dimen,
                        theme = theme,
                        chatSectionTitle = stringResource(
                            com.example.sharedui.R.string.chatting_now
                        ),
                        chatSectionBackground = theme.green8CFFAB,
                        onClickOnChatButton = {},
                        offlineBookingModel = uiState.bookingPlaceHolder,
                        placeHolderState = true,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                }//end items

            }//end LazyColumn

        }//end AnimatedVisibility


        AnimatedVisibility(
            visible = totalOfflineBookingStatus?.loadState?.refresh is LoadState.NotLoading ||
                    cacheOfflineBookingsStatus?.loadState?.refresh is LoadState.NotLoading,
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

            val onlineBookings =
                if (totalOfflineBookingStatus?.loadState?.refresh is LoadState.NotLoading) {
                    totalOfflineBookingStatus
                } else {
                    cacheOfflineBookingsStatus
                }

            Box(
                modifier = Modifier
                    .pullRefresh(pullRefreshState)
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
                    onlineBookings?.let {
                        items(
                            count = it.itemCount
                        ) { count ->

                            //create online booking here
                            onlineBookings[count]?.let { booking ->

                                //create offline booking here
                                OfflineBookingSection(
                                    dimen = dimen,
                                    theme = theme,
                                    chatSectionTitle = stringResource(
                                        com.example.sharedui.R.string.chatting_now
                                    ),
                                    chatSectionBackground = theme.green8CFFAB,
                                    onClickOnChatButton = { onClickOnChatNowButton(booking.doctorId.toInt()) },
                                    offlineBookingModel = booking,
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


        Box(
            modifier = Modifier
                .size(
                    dimen.dimen_7.dp
                )
                .constrainAs(chatFabId) {
                    end.linkTo(
                        parent.end,
                        dimen.dimen_3.dp
                    )
                    bottom.linkTo(
                        parent.bottom,
                        dimen.dimen_3.dp
                    )
                }
                .clip(
                    shape = RoundedCornerShape(
                        size = dimen.dimen_2.dp
                    )
                )
                .background(
                    color = theme.brownLight
                )
                .clickableWithoutHover {
                    onClickOnChatButton()
                },
            contentAlignment = Alignment.Center
        ) {

            Icon(
                painter = painterResource(
                    id = com.example.sharedui.R.drawable.chat_icon
                ),
                contentDescription = "",
                tint = theme.background,
                modifier = Modifier
                    .size(
                        size = dimen.dimen_2_5.dp
                    )
            )

        }//end Box

    }//end ConstraintLayout

    LaunchedEffect(
        key1 = totalOfflineBookingStatus?.loadState?.refresh
    ) {

        if (totalOfflineBookingStatus?.loadState?.refresh is LoadState.NotLoading) {

            onTotalOfflineBookingsBackupCreated()

        }//end if

    }//end LaunchedEffect

}//end OfflineDetailsContent