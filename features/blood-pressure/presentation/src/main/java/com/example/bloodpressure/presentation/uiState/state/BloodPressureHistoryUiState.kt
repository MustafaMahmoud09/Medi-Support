package com.example.bloodpressure.presentation.uiState.state

import androidx.paging.PagingData
import com.example.blood.pressure.domain.model.SimpleBloodPressureModel
import kotlinx.coroutines.flow.Flow

data class BloodPressureHistoryUiState(
    val bloodPressureRecords: Flow<PagingData<SimpleBloodPressureModel>>? = null,
    val bloodPressureRecordsBackup: Flow<PagingData<SimpleBloodPressureModel>>? = null,
    val refreshState: Boolean = false,
    val placeHolderBloodPressure: SimpleBloodPressureModel = SimpleBloodPressureModel(
        id = 0,
        systolic = 0,
        diastolic = 0,
        type = "js",
        createdAt ="js",
        dayName = "js"
    ),
)