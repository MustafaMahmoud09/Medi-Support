package com.example.reminder.presentation.uiState.state

import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalTime

data class ReminderServiceUiState(
    val timeNowState: MutableStateFlow<LocalTime> = MutableStateFlow(LocalTime.now()),
    val reminderNameValue: String = "",
    val reminderTimeValue: String = "",
    val reminderTime: LocalTime = LocalTime.now(),
    val reminderRemainingTimeValue: String = "",
)
