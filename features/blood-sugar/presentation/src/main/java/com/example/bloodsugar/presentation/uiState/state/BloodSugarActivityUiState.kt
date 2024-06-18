package com.example.bloodsugar.presentation.uiState.state

import com.example.blood.sugar.domain.model.AdviceBloodSugarModel
import com.example.blood.sugar.domain.model.SimpleBloodSugarModel
import com.example.libraries.shared.logic.domain.model.DayModel
import java.time.LocalDate

data class BloodSugarActivityUiState(
    val adviceBloodSugarModel: AdviceBloodSugarModel? = null,
    val refreshState: Boolean = false,
    val getBloodSugarChartStatus: GetChartDataStatus = GetChartDataStatus(),
    val lastHistoryBloodSugarRecords: List<SimpleBloodSugarModel> = emptyList(),
    val monthDays: List<DayModel> = emptyList(),
    val currentDayNumber: Int = LocalDate.now().dayOfMonth
)