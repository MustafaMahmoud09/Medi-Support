package com.example.reminder.presentation.uiElement

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.reminder.presentation.uiElement.services.ReminderService

class BootReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        //start reminder service here
        val reminderServiceIntent = Intent(context, ReminderService::class.java)
        context?.startForegroundService(reminderServiceIntent)

    }//end onReceive

}//end BootReminderReceiver