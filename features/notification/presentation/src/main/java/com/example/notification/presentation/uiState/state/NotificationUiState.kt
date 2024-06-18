package com.example.notification.presentation.uiState.state

import androidx.paging.PagingData
import com.example.notification.domain.model.NotificationModel
import kotlinx.coroutines.flow.Flow

data class NotificationUiState(
    val totalNotificationStatus: Flow<PagingData<NotificationModel>>? = null,
    val backupTotalNotificationStatus: Flow<PagingData<NotificationModel>>? = null,
    val refreshState: Boolean = false,
    val notificationPlaceHolder: NotificationModel = NotificationModel(
        id = "",
        notification = "dr mohammed ahmed accepted your booking and now you can",
        read = false,
        type = "",
        bookingId = 0
    )
)