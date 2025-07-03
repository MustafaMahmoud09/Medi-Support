package com.example.bloodsugar.presentation.uiState.state

import com.example.blood.sugar.domain.model.StatusModel
import com.example.libraries.shared.logic.domain.model.DayModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class RecordBloodSugarUiState(
    val sugarLevel: Float = 0f,
    val statusNameSelected: String = "Default",
    val maximumSugarLevel: Float = 10000f,
    val statusState: Boolean = false,
    val statusId: Int = -1,
    val monthDays: List<DayModel> = emptyList(),
    val statusPlaceHolder: StatusModel = StatusModel(
        id = 0,
        status = ""
    ),
    val startRunning: Boolean = true,
    val getBloodSugarStatusState: BloodSugarStatusState = BloodSugarStatusState(),
    val addBloodSugarRecordStatus: AddBloodSugarRecordStatus = AddBloodSugarRecordStatus(),
    val currentDayNumber: Int = LocalDate.now().dayOfMonth,
    val currentDate: String = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
)

data class BloodSugarStatusState(
    val status: List<StatusModel> = emptyList(),
    val loading: Boolean = false,
    val unAuthorized: Boolean = false,
)

data class AddBloodSugarRecordStatus(
    val success: Boolean = false,
    val loading: Boolean = false,
    val internetError: Boolean = false,
    val serverError: Boolean = false,
    val statusNotSelected: Boolean = false,
    val unAuthorized: Boolean = false,
)
