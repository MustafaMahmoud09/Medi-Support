package com.example.shared.preferences.access.`object`

import android.content.Context
import com.example.reminder.data.source.shared.preferences.RunningReminderManager
import com.example.reminder.data.source.shared.preferences.RunningReminderServiceManager

class SharedPreferencesAccessObject(
    private val context: Context
) {

    /**
     * function for provide run reminder manager
     *
     * @return RunReminderManager
     **/
    fun runningReminderManager(): RunningReminderManager {

        return RunningReminderManager(
            context = context
        )

    }//end RunReminderManager


    /**
     * function for provide run reminder service manager
     *
     * @return RunReminderServiceManager
     **/
    fun runningReminderServiceManager(): RunningReminderServiceManager {

        return RunningReminderServiceManager(
            context = context
        )

    }//end RunReminderManager

}//end ReminderPreferencesAccess