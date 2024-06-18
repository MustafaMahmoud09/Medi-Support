package com.example.onlinebooking.presentation.uiState.state

import androidx.paging.PagingData
import com.example.online.booking.domain.model.OnlineDoctorModel
import kotlinx.coroutines.flow.Flow

data class TotalOnlineDoctorsUiState(
    val totalOnlineDoctorsStatus: Flow<PagingData<OnlineDoctorModel>>? = null,
    val cacheOnlineDoctorsStatus: Flow<PagingData<OnlineDoctorModel>>? = null,
    val refreshState: Boolean = false,
    val doctorPlaceHolder: OnlineDoctorModel = OnlineDoctorModel(
        id = 0,
        name = "js",
        image = "js",
        clinicLocation = "js",
        rate = 0f,
        workingHours = 1,
        active = true
    )
)
