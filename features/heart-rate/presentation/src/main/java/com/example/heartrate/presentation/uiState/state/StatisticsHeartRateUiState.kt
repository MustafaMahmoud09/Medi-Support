package com.example.heartrate.presentation.uiState.state

import com.example.heart.rate.domain.domain.model.AdviceHeartRateModel
import com.example.libraries.shared.logic.domain.model.DayModel
import java.time.LocalDate

data class StatisticsHeartRateUiState(
    val adviceHeartRateModel: AdviceHeartRateModel? = null,
    val getHeartRateChartStatus: GetChartDataStatus = GetChartDataStatus(),
    val monthDays: List<DayModel> = emptyList(),
    val currentDayNumber: Int = LocalDate.now().dayOfMonth
)
