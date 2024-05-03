package com.example.offlinebooking.presentation.uiState.state

import com.example.offline.booking.domain.model.OfflineDoctorDetailsModel
import com.example.offline.booking.domain.model.TimeModel
import kotlinx.coroutines.flow.MutableStateFlow

data class OfflineBookingUiState(
    val doctorId: Long = 0,
    val offlineDoctorDetailsStatus: InfiniteGetterStatus<OfflineDoctorDetailsModel> = InfiniteGetterStatus(
        loading = true
    ),
    val dateTimeStatus: InfiniteGetterStatus<List<TimeModel>> = InfiniteGetterStatus(
        data = emptyList()
    ),
    val dateIdSelected: MutableStateFlow<Long> = MutableStateFlow(-1),
    val timeIdSelected: Long = -1,
    val bookOfflineAppointmentStatus: BookOfflineAppointmentStatus = BookOfflineAppointmentStatus(),
    val startRunning: Boolean = true,
)

data class BookOfflineAppointmentStatus(
    val success: Boolean = false,
    val loading: Boolean = false,
    val internetError: Boolean = false,
    val serverError: Boolean = false,
    val appointmentNotValid: Boolean = false,
    val appointNotSelected: Boolean = false
)
