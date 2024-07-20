package com.example.offlinebooking.presentation.uiState.state

import androidx.paging.PagingData
import com.example.offline.booking.domain.model.OfflineBookingModel
import kotlinx.coroutines.flow.Flow


data class OfflineDetailsUiState(
    val totalOfflineBookingStatus: Flow<PagingData<OfflineBookingModel>>? = null,
    val cacheTotalOfflineBookingStatus: Flow<PagingData<OfflineBookingModel>>? = null,
    val refreshState: Boolean = false,
    val bookingPlaceHolder: OfflineBookingModel = OfflineBookingModel(
        id = 0,
        doctorName = "js",
        specialization = "js",
        clinicLocation = "js",
        date = "js",
        doctorId = -1
    )
)