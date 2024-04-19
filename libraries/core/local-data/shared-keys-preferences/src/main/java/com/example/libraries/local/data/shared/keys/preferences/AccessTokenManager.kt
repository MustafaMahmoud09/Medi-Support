package com.example.libraries.local.data.shared.keys.preferences

import com.sun.tools.javac.util.Context

class RunningReminderServiceManager(
    context: Context
) : SharedPreferencesManager(context) {

    //TODO:: Function For Set Run Reminder
    fun setReminderServiceState(value: Boolean) {

        //set run reminder value here
        sharedPreferences.edit {
            putBoolean(RUN_SERVICE_KEY_NAME, value)
        }//end edit

    }//end setReminderServiceState

    //TODO:: Function For Get Run Reminder
    fun getReminderServiceState(): Boolean {

        return sharedPreferences.getBoolean(RUN_SERVICE_KEY_NAME, false)

    }//end getReminderServiceState


    companion object {
        private const val RUN_SERVICE_KEY_NAME = "run_service_key"
    }//end companion object

}//end RunServiceManager