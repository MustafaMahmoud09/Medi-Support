package com.example.bmi.presentation.uiState.state

import com.example.bmi.domain.model.AdviceBMIModel
import com.example.bmi.domain.model.SimpleBMIModel
import com.example.libraries.shared.logic.domain.model.DayModel
import java.time.LocalDate

data class BMIActivityUiState(
    val adviceBMIModel: AdviceBMIModel? = null,
    val getBMIChartStatus: GetChartDataStatus = GetChartDataStatus(),
    val lastHistoryBMIRecords: List<SimpleBMIModel> = emptyList(),
    val monthDays: List<DayModel> = emptyList(),
    val refreshState: Boolean = false,
    val currentDayNumber: Int = LocalDate.now().dayOfMonth
)