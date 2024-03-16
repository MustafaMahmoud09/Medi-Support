package com.example.reminder.presentation.uiState.state

import android.app.Notification
import android.media.MediaPlayer
import com.example.reminder.domaim.domain.model.reminder.NearestReminderPresentationModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalTime

data class ReminderServiceUiState(
    val timeNowState: MutableStateFlow<LocalTime> = MutableStateFlow(LocalTime.now()),
    val reminderNameValue: String = "",
    val reminderTimeValue: String = "",
    val reminderNotificationVisible: MutableStateFlow<Boolean> = MutableStateFlow(false),
    val currentNearestReminder: NearestReminderPresentationModel = NearestReminderPresentationModel(
        id = 0L,
        name = "",
        time = LocalTime.now().plusHours(1),
        day = "",
        differentDays = 0
    ),
    val lastNearestReminder: NearestReminderPresentationModel = NearestReminderPresentationModel(
        id = 0L,
        name = "",
        time = LocalTime.now().plusHours(1),
        day = "",
        differentDays = 0
    ),
    val reminderRemainingTimeValue: String = "",
    val alarmSound: MediaPlayer? = null,
    val alarmNotification: Notification? = null
)
