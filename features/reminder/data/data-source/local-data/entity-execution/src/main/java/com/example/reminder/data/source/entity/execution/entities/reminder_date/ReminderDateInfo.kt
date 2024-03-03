package com.example.reminder.data.source.entity.execution.entities.reminder_date

import com.example.reminder.data.source.entity.execution.entities.day.DayInfo
import com.example.reminder.data.source.entity.execution.entities.reminder.ReminderInfo

//create columns name in this object
object ReminderDateInfo {

    const val REMINDER_DATE_TABLE_NANE = "reminder_dates"

    const val ID_COLUMN_NAME = "reminder_date_id"

    const val REMINDER_ID_COLUMN_NAME = ReminderInfo.ID_COLUMN_NAME

    const val DAY_ID_COLUMN_NAME = DayInfo.ID_COLUMN_NAME

    const val CREATED_AT_COLUMN_NAME = "created_at"

}//end ReminderDateColumns