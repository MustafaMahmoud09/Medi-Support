package com.example.reminder.data.source.shared.preferences

import android.content.Context
import androidx.core.content.edit
import com.example.libraries.local.data.shared.preferences.manager.SharedPreferencesManager

class RunningReminderManager(
    context: Context
) : SharedPreferencesManager(context) {


    //TODO:: Function For Set Run Reminder
    fun setRunningReminderFeatureState(value: Boolean) {

        //set run reminder value here
        sharedPreferences.edit {
            putBoolean(RUN_REMINDER_KEY_NAME, value)
        }//end edit

    }//end setRunReminder

    //TODO:: Function For Get Run Reminder
    fun getRunningReminderFeatureState(): Boolean {

        return sharedPreferences.getBoolean(RUN_REMINDER_KEY_NAME, false)

    }//end getRunReminder


    companion object {
        private const val RUN_REMINDER_KEY_NAME = "run_reminder_key"
    }//end companion object

}//end RunReminderManager