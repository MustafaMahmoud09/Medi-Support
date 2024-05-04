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
import com.example.offline.booking.domain.model.OfflineBookingModel
import com.example.offlinebooking.presentation.uiElement.components.items.OfflineBookingSection
import com.example.offlinebooking.presentation.uiState.state.OfflineDetailsUiState
import com.example.offlinebooking.presentation.uiState.viewModel.OfflineDetailsViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun OfflineDetailsScreen(
    viewModel: OfflineDetailsViewModel = hiltViewModel(),
    dimen: CustomDimen,
    theme: CustomTheme,
    navigateToChatNavGraph: () -> Unit
) {
    //get screen state here
    val state = viewModel.state.collectAsState()

    //create offline details content here
    OfflineDetailsContent(
        dimen = dimen,
        theme = theme,
        onClickOnChatButton = navigateToChatNavGraph,
        uiState = state.value,
        totalOfflineBookingStatus = state.value.totalOfflineBookingStatus?.collectAsLazyPagingItems()
    )

}//end OfflineDetailsScreen

@Composable
private fun OfflineDetailsContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickOnChatButton: () -> Unit,
    uiState: OfflineDetailsUiState,
    totalOfflineBookingStatus: LazyPagingItems<OfflineBookingModel>?
) {


    AnimatedVisibility(
        visible = totalOfflineBookingStatus?.loadState?.refresh is LoadState.Loading,
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
        visible = totalOfflineBookingStatus?.loadState?.refresh is LoadState.NotLoading,
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
            totalOfflineBookingStatus?.let {
                items(
                    count = it.itemCount
                ) { count ->

                    //create online booking here
                    totalOfflineBookingStatus[count]?.let { booking ->

                        //create offline booking here
                        OfflineBookingSection(
                            dimen = dimen,
                            theme = theme,
                            chatSectionTitle = stringResource(
                                com.example.sharedui.R.string.chatting_now
                            ),
                            chatSectionBackground = theme.green8CFFAB,
                            onClickOnChatButton = onClickOnChatButton,
                            offlineBookingModel = booking,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                }

            }//end items

        }//end LazyColumn

    }//end AnimatedVisibility

}//end OfflineDetailsContent