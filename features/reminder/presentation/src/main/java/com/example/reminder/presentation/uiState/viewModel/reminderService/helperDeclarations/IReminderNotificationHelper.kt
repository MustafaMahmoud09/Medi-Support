package com.example.reminder.presentation.uiState.viewModel.reminderService.helperDeclarations

import android.app.Notification
import android.media.MediaPlayer

interface IReminderNotificationHelper {

    val reminderNotificationId: Int

    fun createReminderNotification(
        time: String
    ): Notification

    fun createReminderNotificationSound(): MediaPlayer

    fun cancelReminderNotification()

    fun notifyReminderNotification(notification: Notification)

}//end INotificationHelper