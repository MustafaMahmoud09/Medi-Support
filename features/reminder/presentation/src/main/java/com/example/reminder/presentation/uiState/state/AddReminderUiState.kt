package com.example.reminder.presentation.uiState.state

data class AddReminderUiState(
    val isDatePickerVisible: Boolean = false,
    val medicamentName: String = "",
    val daysValue: String = "",
    val timeValue: String = "",
)



