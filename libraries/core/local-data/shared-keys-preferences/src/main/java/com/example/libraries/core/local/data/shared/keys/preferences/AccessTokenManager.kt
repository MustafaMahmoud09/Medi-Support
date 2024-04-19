package com.example.libraries.core.local.data.shared.keys.preferences

import android.content.Context
import androidx.core.content.edit
import com.example.libraries.local.data.shared.preferences.manager.SharedPreferencesManager

class AccessTokenManager(
    context: Context
) : SharedPreferencesManager(context) {


    //TODO:: Function For Set Run Reminder
    fun setAccessToken(value: String) {

        //set run reminder value here
        sharedPreferences.edit {
            putString(ACCESS_TOKEN_KEY_NAME, value)
        }//end edit

    }//end setRunReminder

    //TODO:: Function For Get Run Reminder
    fun getAccessToken(): String {

        return sharedPreferences.getString(ACCESS_TOKEN_KEY_NAME, "") ?: ""

    }//end getRunReminder


    companion object {
        private const val ACCESS_TOKEN_KEY_NAME = "access-token"
    }//end companion object

}//end RunReminderManager