package com.example.reminder.data.source.shared.preferences

import android.content.Context
import com.example.reminder.data.source.shared.preferences.managers.child.RunReminderManager

class ReminderPreferencesAccess(
    private val context: Context
) {

    /**
     * function for provide run reminder manager
     *
     * @return RunReminderManager
     **/
    fun runReminderManager(): RunReminderManager {

        return RunReminderManager(
            context = context
        )

    }//end RunReminderManager

}//end ReminderPreferencesAccess