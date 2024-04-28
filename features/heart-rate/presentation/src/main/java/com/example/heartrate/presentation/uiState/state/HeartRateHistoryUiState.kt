package com.example.heartrate.presentation.uiState.state

import androidx.paging.PagingData
import com.example.heart.rate.domain.domain.model.SimpleHeartRateModel
import kotlinx.coroutines.flow.Flow

data class HeartRateHistoryUiState(
    val heartRateRecords: Flow<PagingData<SimpleHeartRateModel>>? = null,
    val placeHolderHeartRate: SimpleHeartRateModel = SimpleHeartRateModel(
        id = 0,
        rate = 0,
        type = "js",
        createdAt = "js",
        dayName = "js"
    ),
)
