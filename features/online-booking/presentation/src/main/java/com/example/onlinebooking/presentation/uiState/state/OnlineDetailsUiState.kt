package com.example.onlinebooking.presentation.uiState.state

import androidx.paging.PagingData
import com.example.online.booking.domain.model.OnlineBookingModel
import kotlinx.coroutines.flow.Flow

data class OnlineDetailsUiState(
    val totalOnlineBookingStatus: Flow<PagingData<OnlineBookingModel>>? = null,
    val bookingPlaceHolder: OnlineBookingModel = OnlineBookingModel(
        id = 0,
        doctorName = "js",
        specialization = "js",
        activeStatus = true,
        bookingStatus = 0
    )
)