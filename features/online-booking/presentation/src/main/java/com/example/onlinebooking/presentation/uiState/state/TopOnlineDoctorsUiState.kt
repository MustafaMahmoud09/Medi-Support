package com.example.onlinebooking.presentation.uiState.state

import com.example.online.booking.domain.model.OnlineDoctorModel

data class TopOnlineDoctorsUiState(
    val getTopOnlineDoctorsStatus: InfiniteGetterStatus<List<OnlineDoctorModel>> = InfiniteGetterStatus(
        data = emptyList()
    ),
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