package com.example.reminder.data.source.entity.execution.complexQuery

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.reminder.data.source.entity.execution.entities.day.DayEntity
import com.example.reminder.data.source.entity.execution.entities.reminder.ReminderEntity
import com.example.reminder.data.source.entity.execution.entities.reminder_date.ReminderDateEntity
import com.example.reminder.data.source.entity.execution.entities.reminder_date.ReminderDateInfo
import com.example.reminder.domain.entity.interfaces.complexQuery.IReminderWithDays

data class ReminderWithDays(
    @Embedded override val reminder: ReminderEntity,
    @Relation(
        parentColumn = ReminderDateInfo.REMINDER_ID_COLUMN_NAME,
        entityColumn = ReminderDateInfo.DAY_ID_COLUMN_NAME,
        associateBy = Junction(ReminderDateEntity::class)
    ) override val days: List<DayEntity>
) : IReminderWithDays