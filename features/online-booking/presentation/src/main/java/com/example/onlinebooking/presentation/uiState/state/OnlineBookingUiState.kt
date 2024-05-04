package com.example.onlinebooking.presentation.uiState.state

import com.example.online.booking.domain.model.OnlineDoctorDetailsModel

internal data class OnlineBookingUiState(
    val doctorId: Long = 0,
    val onlineDoctorDetailsStatus: GetDoctorDetailsStatus = GetDoctorDetailsStatus(),
    val startRunning: Boolean = true,
    val bookOnlineAppointmentStatus: BookOfflineAppointmentStatus = BookOfflineAppointmentStatus()
)

data class BookOfflineAppointmentStatus(
    val success: Boolean = false,
    val loading: Boolean = false,
    val internetError: Boolean = false,
    val serverError: Boolean = false,
    val doctorNotOnline: Boolean = false,
)

data class GetDoctorDetailsStatus(
    val data: OnlineDoctorDetailsModel? = null,
    val loading: Boolean = true,
    val doctorDeleted: Boolean = false,
)