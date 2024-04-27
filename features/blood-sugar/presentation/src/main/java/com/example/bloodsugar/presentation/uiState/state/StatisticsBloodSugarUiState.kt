package com.example.bloodsugar.presentation.uiState.state

import com.example.blood.sugar.domain.model.AdviceBloodSugarModel
import com.example.libraries.shared.logic.domain.model.DayModel
import java.time.LocalDate

data class StatisticsBloodSugarUiState(
    val adviceBloodSugarModel: AdviceBloodSugarModel? = null,
    val getBloodSugarChartStatus: GetChartDataStatus = GetChartDataStatus(),
    val monthDays: List<DayModel> = emptyList(),
    val currentDayNumber: Int = LocalDate.now().dayOfMonth
)
