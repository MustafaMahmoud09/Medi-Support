package com.example.shared.preferences.access.`object`

import android.content.Context
import com.example.libraries.core.local.data.shared.keys.preferences.AccessTokenManager
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


    /**
     * function for provide access token manager
     *
     * @return AccessTokenManager
     **/
    fun accessTokenManager(): AccessTokenManager {

        return AccessTokenManager(
            context = context
        )

    }//end accessTokenManager

}//end ReminderPreferencesAccess