package com.example.reminder.presentation.uiState.state

import android.app.Notification
import android.media.MediaPlayer
import com.example.reminder.domaim.domain.model.reminder.NearestReminderPresentationModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalTime

data class ReminderServiceUiState(
    val currentTimeState: MutableStateFlow<LocalTime> = MutableStateFlow(LocalTime.now()),
    val reminderTimeValue: String = "",
    val currentNearestReminder: NearestReminderPresentationModel = NearestReminderPresentationModel(
        id = 0L,
        name = "",
        time = LocalTime.now().plusHours(1),
        day = "",
        dayNumber = 0
    ),
    val reminderRemainingTimeValue: String = "",
    val alarmSound: MediaPlayer? = null,
    val alarmNotification: Notification? = null
)
