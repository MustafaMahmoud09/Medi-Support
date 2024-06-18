@file:OptIn(ExperimentalMaterialApi::class)

package com.example.notification.presentation.uiElement.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.notification.domain.model.NotificationModel
import com.example.notification.presentation.uiElement.components.items.NotificationHeaderSection
import com.example.notification.presentation.uiElement.components.items.NotificationSection
import com.example.notification.presentation.uiState.state.NotificationUiState
import com.example.notification.presentation.uiState.viewModel.NotificationViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlin.reflect.KFunction0

@Composable
fun NotificationScreen(
    viewModel: NotificationViewModel = hiltViewModel(),
    navigateToOnlineRoomDestination: (Int) -> Unit
) {
    val state = viewModel.state.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.value.refreshState,
        onRefresh = viewModel::onRefreshNotifications
    )

    NotificationContent(
        uiState = state.value,
        notifications = state.value.totalNotificationStatus?.collectAsLazyPagingItems(),
        backupNotification = state.value.backupTotalNotificationStatus?.collectAsLazyPagingItems(),
        pullRefreshState = pullRefreshState,
        onClickOnNotification = { notificationId, notificationType, bookingId, notificationState ->
            if (notificationType == "video_call") {
                navigateToOnlineRoomDestination(bookingId.toInt())
            }//end if
            viewModel.onReadNotificationById(
                id = notificationId,
                read = notificationState
            )
        },
        onClickOnReadAllNotification = viewModel::onReadAllNotification,
        onNotificationBackupCreated = viewModel::onNotificationBackupCreated
    )

}//end NotificationScreen

@Composable
private fun NotificationContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    uiState: NotificationUiState,
    notifications: LazyPagingItems<NotificationModel>?,
    onClickOnNotification: (String, String, Long, Boolean) -> Unit,
    backupNotification: LazyPagingItems<NotificationModel>?,
    onNotificationBackupCreated: KFunction0<Unit>,
    onClickOnReadAllNotification: KFunction0<Unit>,
    pullRefreshState: PullRefreshState
) {


    if (
        notifications?.loadState?.refresh !is LoadState.NotLoading &&
        backupNotification?.loadState?.refresh !is LoadState.NotLoading
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
        ) {

            //create notification header item here
            item(
                key = "Notification header id"
            ) {

                //create notification header section here
                NotificationHeaderSection(
                    dimen = dimen,
                    theme = theme,
                    title = stringResource(
                        id = R.string.notifications
                    ),
                    readTitle = stringResource(
                        id = R.string.mark_all_read
                    ),
                    readCheckBoxState = true,
                    readOnCheckBoxChanged = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = dimen.dimen_2.dp
                        )
                )

            }//end item


            //create notification items here
            items(
                count = 10
            ) {

                //create single notification here
                NotificationSection(
                    dimen = dimen,
                    theme = theme,
                    notification = uiState.notificationPlaceHolder,
                    placeHolderState = true,
                    onClick = { _, _, _, _ -> },
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end items

        }//end LazyColumn

    }//end if


    if (
        notifications?.loadState?.refresh is LoadState.NotLoading ||
        backupNotification?.loadState?.refresh is LoadState.NotLoading
    ) {

        Box(
            modifier = Modifier
                .pullRefresh(pullRefreshState)
        ) {

            val notificationsResult =
                if (notifications?.loadState?.refresh is LoadState.NotLoading) {
                    notifications
                } else {
                    backupNotification
                }

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
            ) {

                //create notification header item here
                item(
                    key = "Notification header id"
                ) {

                    //create notification header section here
                    NotificationHeaderSection(
                        dimen = dimen,
                        theme = theme,
                        title = stringResource(
                            id = R.string.notifications
                        ),
                        readTitle = stringResource(
                            id = R.string.mark_all_read
                        ),
                        readCheckBoxState = true,
                        readOnCheckBoxChanged = onClickOnReadAllNotification,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = dimen.dimen_2.dp
                            )
                    )

                }//end item

                //create notification items here
                notificationsResult?.itemCount?.let {
                    items(
                        count = it
                    ) {

                        //create single notification here
                        notificationsResult[it]?.let { it1 ->
                            NotificationSection(
                                dimen = dimen,
                                theme = theme,
                                notification = it1,
                                onClick = onClickOnNotification,
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
                modifier = Modifier.align(Alignment.TopCenter),
            )

        }//end Box

    }//end if


    LaunchedEffect(
        key1 = notifications?.loadState?.refresh
    ) {

        if (notifications?.loadState?.refresh is LoadState.NotLoading) {

            onNotificationBackupCreated()

        }//end if

    }//end LaunchedEffect

}//end NotificationContent