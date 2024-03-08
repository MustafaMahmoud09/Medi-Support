package com.example.reminder.data.source.shared.preferences.managers.child

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.reminder.data.source.shared.preferences.managers.SharedPreferencesManager

class RunningReminderManager(
    context: Context
) : SharedPreferencesManager() {

    //create shared preferences object here
    private var reminderSharedPreferences: SharedPreferences = context.create()


    //TODO:: Function For Set Run Reminder
    fun setRunningReminderFeatureState(value: Boolean) {

        //set run reminder value here
        reminderSharedPreferences.edit {
            putBoolean(RUN_REMINDER_KEY_NAME, value)
        }//end edit

    }//end setRunReminder

    //TODO:: Function For Get Run Reminder
    fun getRunningReminderFeatureState(): Boolean {

        return reminderSharedPreferences.getBoolean(RUN_REMINDER_KEY_NAME, false)

    }//end getRunReminder


    companion object {
        private const val RUN_REMINDER_KEY_NAME = "run_reminder_key"
    }//end companion object

}//end RunReminderManager