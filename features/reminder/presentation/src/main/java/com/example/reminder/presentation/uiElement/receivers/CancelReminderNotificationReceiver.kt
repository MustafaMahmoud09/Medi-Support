package com.example.reminder.presentation.uiElement.receivers


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.reminder.presentation.uiState.viewModel.ReminderServiceViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//@EntryPoint
class CancelReminderNotificationReceiver : BroadcastReceiver() {

    @Inject
    lateinit var viewModel: ReminderServiceViewModel
    override fun onReceive(context: Context?, intent: Intent?) {

        viewModel.onReminderNotificationCanceled()

    }//end onReceive


}//end SnoozeReminderNotificationReceiver