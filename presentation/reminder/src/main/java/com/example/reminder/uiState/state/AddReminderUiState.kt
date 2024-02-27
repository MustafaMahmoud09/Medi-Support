package com.example.reminder.uiState.state

data class AddReminderUiState(
    val isDatePickerVisible: Boolean = false,
    val medicamentName: String = "",
    val daysValue: String = "",
    val timeValue: String = "",
)



