package com.example.offlinebooking.presentation.uiState.state

import androidx.paging.PagingData
import com.example.offline.booking.domain.model.OfflineDoctorModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

data class SearchUiState(
    val searchKey: MutableStateFlow<String> = MutableStateFlow(""),
    val focusOnSearch: Boolean = false,
    val searchOfflineDoctorsStatus: Flow<PagingData<OfflineDoctorModel>>? = null,
    val pagerStack: List<Int> = listOf(0),
    val doctorPlaceHolder: OfflineDoctorModel = OfflineDoctorModel(
        id = 0,
        name = "js",
        image = "js",
        clinicLocation = "js",
        rate = 0f,
        workingHours = 1
    )
)
