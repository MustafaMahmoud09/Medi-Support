package com.example.reminder.data.source.shared.preferences.managers.child

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.reminder.data.source.shared.preferences.managers.SharedPreferencesManager

class RunningReminderServiceManager(
    context: Context
) : SharedPreferencesManager() {

    //create shared preferences object here
    private var reminderSharedPreferences: SharedPreferences = context.create()


    //TODO:: Function For Set Run Reminder
    fun setReminderServiceState(value: Boolean) {

        //set run reminder value here
        reminderSharedPreferences.edit {
            putBoolean(RUN_SERVICE_KEY_NAME, value)
        }//end edit

    }//end setReminderServiceState

    //TODO:: Function For Get Run Reminder
    fun getReminderServiceState(): Boolean {

        return reminderSharedPreferences.getBoolean(RUN_SERVICE_KEY_NAME, false)

    }//end getReminderServiceState


    companion object {
        private const val RUN_SERVICE_KEY_NAME = "run_service_key"
    }//end companion object

}//end RunServiceManager