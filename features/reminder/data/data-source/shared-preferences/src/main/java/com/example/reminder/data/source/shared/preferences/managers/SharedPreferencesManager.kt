package com.example.reminder.data.source.shared.preferences.managers

import android.content.Context
import android.content.SharedPreferences

abstract class SharedPreferencesManager {

    /**
     * function for create shared preferences file
     *
     * @return SharedPreferences
     * **/
    fun Context.create(): SharedPreferences {

        //create shared preferences
        return getSharedPreferences(
            REMINDER_SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )

    }//end create

    companion object {

        //shared preferences name
        private const val REMINDER_SHARED_PREFERENCES_NAME = "reminder_medi_support"

    }//end companion

}//end SharedPreferencesManager