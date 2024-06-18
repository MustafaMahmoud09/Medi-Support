package com.example.heartrate.presentation.uiState.state

import com.example.heart.rate.domain.domain.model.AdviceHeartRateModel
import com.example.heart.rate.domain.domain.model.SimpleHeartRateModel
import com.example.libraries.shared.logic.domain.model.DayModel
import java.time.LocalDate

data class BloodSugarActivityUiState(
    val adviceHeartRateModel: AdviceHeartRateModel? = null,
    val getHeartRateChartStatus: GetChartDataStatus = GetChartDataStatus(),
    val lastHistoryHeartRateRecords: List<SimpleHeartRateModel> = emptyList(),
    val refreshState: Boolean = false,
    val monthDays: List<DayModel> = emptyList(),
    val currentDayNumber: Int = LocalDate.now().dayOfMonth
)