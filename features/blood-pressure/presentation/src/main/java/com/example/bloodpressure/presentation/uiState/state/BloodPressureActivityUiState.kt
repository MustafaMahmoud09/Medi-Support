package com.example.bloodpressure.presentation.uiState.state

import com.example.blood.pressure.domain.model.AdviceBloodPressureModel
import com.example.blood.pressure.domain.model.SimpleBloodPressureModel
import com.example.libraries.shared.logic.domain.model.DayModel
import java.time.LocalDate

data class BloodPressureActivityUiState(
    val adviceBloodPressureModel: AdviceBloodPressureModel? = null,
    val monthDays: List<DayModel> = emptyList(),
    val systolicResult: GetChartDataStatus = GetChartDataStatus(),
    val diastolicResult: GetChartDataStatus = GetChartDataStatus(),
    val currentDayNumber: Int = LocalDate.now().dayOfMonth,
    val lastHistoryRecords: List<SimpleBloodPressureModel> = emptyList()
)