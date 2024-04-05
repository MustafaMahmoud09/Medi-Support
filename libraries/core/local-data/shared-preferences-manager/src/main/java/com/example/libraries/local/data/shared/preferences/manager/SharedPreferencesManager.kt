package com.example.libraries.local.data.shared.preferences.manager

import android.content.Context
import android.content.SharedPreferences

abstract class SharedPreferencesManager(context: Context) {

    var sharedPreferences: SharedPreferences = context.create()

    /**
     * function for create shared preferences file
     *
     * @return SharedPreferences
     * **/
    private fun Context.create(): SharedPreferences {

        //create shared preferences
        return getSharedPreferences(
            REMINDER_SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )

    }//end create

    companion object {

        //shared preferences name
        private const val REMINDER_SHARED_PREFERENCES_NAME = "medi_support_preferences"

    }//end companion

}//end SharedPreferencesManager