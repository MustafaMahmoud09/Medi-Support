package com.example.reminder.presentation.uiState.state

import com.example.reminder.domaim.domain.model.reminder.NearestReminderPresentationModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalTime

data class ReminderServiceUiState(
    val timeNowState: MutableStateFlow<LocalTime> = MutableStateFlow(LocalTime.now()),
    val reminderNameValue: String = "",
    val reminderTimeValue: String = "",
    val reminderNotificationVisible: MutableStateFlow<Boolean> = MutableStateFlow(false),
    val nearestReminder: NearestReminderPresentationModel = NearestReminderPresentationModel(
        id = 0L,
        name = "",
        time = LocalTime.now().plusHours(1),
        day = "",
        differentDays = 0
    ),
    val lastReminder: NearestReminderPresentationModel = NearestReminderPresentationModel(
        id = 0L,
        name = "",
        time = LocalTime.now().plusHours(1),
        day = "",
        differentDays = 0
    ),
    val reminderRemainingTimeValue: String = "",
)
