package com.example.offlinebooking.presentation.uiState.state

import com.example.offline.booking.domain.model.OfflineDoctorModel

data class TopOfflineDoctorsUiState(
    val getTopOfflineDoctorsStatus: InfiniteGetterStatus<List<OfflineDoctorModel>> = InfiniteGetterStatus(
        data = emptyList()
    ),
    val doctorPlaceHolder: OfflineDoctorModel = OfflineDoctorModel(
        id = 0,
        name = "js",
        image = "js",
        clinicLocation = "js",
        rate = 0f,
        workingHours = 1
    )
)