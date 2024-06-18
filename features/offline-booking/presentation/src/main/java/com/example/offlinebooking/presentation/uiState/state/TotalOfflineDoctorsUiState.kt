package com.example.offlinebooking.presentation.uiState.state

import androidx.paging.PagingData
import com.example.offline.booking.domain.model.OfflineDoctorModel
import kotlinx.coroutines.flow.Flow

data class TotalOfflineDoctorsUiState(
    val totalOfflineDoctorsStatus: Flow<PagingData<OfflineDoctorModel>>? = null,
    val totalOfflineDoctorsBackupStatus: Flow<PagingData<OfflineDoctorModel>>? = null,
    val refreshState: Boolean = false,
    val doctorPlaceHolder: OfflineDoctorModel = OfflineDoctorModel(
        id = 0,
        name = "js",
        image = "js",
        clinicLocation = "js",
        rate = 0f,
        workingHours = 1
    )
)
