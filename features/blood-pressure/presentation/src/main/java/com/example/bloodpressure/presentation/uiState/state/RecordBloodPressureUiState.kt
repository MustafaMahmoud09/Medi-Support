package com.example.bloodpressure.presentation.uiState.state

import com.example.blood.pressure.domain.model.AdviceBloodPressureModel
import com.example.libraries.shared.logic.domain.model.DayModel
import java.time.LocalDate

data class RecordBloodPressureUiState(
    val systolicBloodPressure: Int = 100,
    val diastolicBloodPressure: Int = 80,
    val startRunning: Boolean = true,
    val addBloodPressureRecordStatus: AddBloodPressureRecordStatus = AddBloodPressureRecordStatus(),
    val monthDays: List<DayModel> = emptyList(),
    val adviceBloodPressureModel: AdviceBloodPressureModel? = null,
    val currentDayNumber: Int = LocalDate.now().dayOfMonth
)

data class AddBloodPressureRecordStatus(
    val success: Boolean = false,
    val loading: Boolean = false,
    val internetError: Boolean = false,
    val serverError: Boolean = false,
)

