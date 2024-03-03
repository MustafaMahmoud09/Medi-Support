package com.example.reminder.data.source.shared.preferences.managers.child

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.reminder.data.source.shared.preferences.managers.SharedPreferencesManager

class RunReminderManager(
    context: Context
) : SharedPreferencesManager() {

    //create shared preferences object here
    private var reminderSharedPreferences: SharedPreferences = context.create()


    //TODO:: Function For Set Run Reminder
    fun setRunReminder(value: Boolean) {

        //set run reminder value here
        reminderSharedPreferences.edit {
            putBoolean(RUN_REMINDER_KEY_NAME, value)
        }//end edit

    }//end setRunReminder

    //TODO:: Function For Get Run Reminder
    fun getRunReminder(): Boolean {

        return reminderSharedPreferences.getBoolean(RUN_REMINDER_KEY_NAME, true)

    }//end getRunReminder


    companion object {
        private const val RUN_REMINDER_KEY_NAME = "run_reminder_key"
    }//end companion object

}//end RunReminderManager