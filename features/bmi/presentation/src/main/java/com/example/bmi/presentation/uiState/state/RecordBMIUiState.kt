package com.example.bmi.presentation.uiState.state

data class RecordBMIUiState(
    val gender: Int = 0,
    val age: Float = 25f,
    val height: Float = 160f,
    val weight: Float = 65f,
    val addBMIRecordStatus: AddBloodSugarRecordStatus = AddBloodSugarRecordStatus(),
    val startRunning: Boolean = true,
)


data class AddBloodSugarRecordStatus(
    val success: Boolean = false,
    val loading: Boolean = false,
    val internetError: Boolean = false,
    val serverError: Boolean = false,
    val unAuthorized: Boolean = false
)