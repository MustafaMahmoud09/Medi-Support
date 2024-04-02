package com.example.reminder.presentation.uiElement

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import com.example.reminder.domain.usecase.interfaces.IGetReminderServiceRunningStateUseCase
import com.example.reminder.presentation.uiElement.services.ReminderService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BootReminderReceiver : DeviceAdminReceiver() {

    @Inject
    lateinit var getReminderServiceRunningStateUseCase: IGetReminderServiceRunningStateUseCase

    override fun onEnabled(context: Context, intent: Intent) {

        //if reminder service running state equal true
        if (getReminderServiceRunningStateUseCase()) {

            //start reminder service here
            val reminderServiceIntent = Intent(context, ReminderService::class.java)
            context.startForegroundService(reminderServiceIntent)

        }//end if

    }//end onEnabled


}//end BootReminderReceiver