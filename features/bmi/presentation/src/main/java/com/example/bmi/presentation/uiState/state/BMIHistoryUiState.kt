package com.example.bmi.presentation.uiState.state

import androidx.paging.PagingData
import com.example.bmi.domain.model.SimpleBMIModel
import kotlinx.coroutines.flow.Flow

data class BloodSugarHistoryUiState(
    val bmiRecords: Flow<PagingData<SimpleBMIModel>>? = null,
    val placeHolderBloodSugar: SimpleBMIModel = SimpleBMIModel(
        id = 0,
        result = "js",
        type = "js",
        createdAt = "js",
        dayName = "js"
    ),
)