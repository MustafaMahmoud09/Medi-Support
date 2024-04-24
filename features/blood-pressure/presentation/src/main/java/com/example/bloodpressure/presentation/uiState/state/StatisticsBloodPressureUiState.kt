package com.example.bloodpressure.presentation.uiState.state

import com.example.blood.pressure.domain.model.AdviceBloodPressureModel
import com.example.libraries.shared.logic.domain.model.DayModel
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.entryModelOf
import java.time.LocalDate

data class StatisticsBloodPressureUiState(
    val adviceBloodPressureModel: AdviceBloodPressureModel? = null,
    val monthDays: List<DayModel> = emptyList(),
    val systolicResult: GetChartDataStatus = GetChartDataStatus(),
    val diastolicResult: GetChartDataStatus = GetChartDataStatus(),
    val currentDayNumber: Int = LocalDate.now().dayOfMonth
)
