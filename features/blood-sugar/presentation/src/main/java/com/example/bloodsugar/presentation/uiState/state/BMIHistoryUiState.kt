package com.example.bloodsugar.presentation.uiState.state

import androidx.paging.PagingData
import com.example.blood.sugar.domain.model.SimpleBloodSugarModel
import kotlinx.coroutines.flow.Flow

data class BMIHistoryUiState(
    val bloodSugarRecords: Flow<PagingData<SimpleBloodSugarModel>>? = null,
    val placeHolderBloodSugar: SimpleBloodSugarModel = SimpleBloodSugarModel(
        id = 0,
        level = (0).toDouble(),
        type = "js",
        createdAt = "js",
        dayName = "js"
    ),
)