package com.example.reminder.presentation.uiElement.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.reminder.presentation.uiState.viewModel.ReminderServiceViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CancelReminderNotificationService : Service() {

    @Inject
    lateinit var viewModel: ReminderServiceViewModel

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        viewModel.onReminderNotificationCanceled()

        return START_NOT_STICKY

    }//end onStartCommand

}//end CancelReminderNotificationReceiver