package com.example.reminder.data.source.entity.execution.entities.reminder

import com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo

//create columns name in this object
object ReminderInfo {

    const val REMINDER_TABLE_NAME = "reminders"

    const val ID_COLUMN_NAME = "reminder_id"

    const val NAME_COLUMN_NAME = "reminder_name"

    const val TIME_COLUMN_NAME = "time"

    const val STATUS_COLUMN_NAME = "status"

    const val USER_ID_COLUMN_NAME = com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo.ID_COLUMN_NAME

    const val CREATED_AT_COLUMN_NAME = "created_at"

}//end ReminderInfo