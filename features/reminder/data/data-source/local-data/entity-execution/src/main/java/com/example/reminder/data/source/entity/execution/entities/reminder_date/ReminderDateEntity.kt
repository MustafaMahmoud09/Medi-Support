package com.example.reminder.data.source.entity.execution.entities.reminder_date

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.reminder.data.source.entity.execution.entities.day.DayEntity
import com.example.reminder.data.source.entity.execution.entities.day.DayInfo
import com.example.reminder.data.source.entity.execution.entities.reminder.ReminderEntity
import com.example.reminder.data.source.entity.execution.entities.reminder.ReminderInfo
import com.example.reminder.domain.entity.interfaces.IReminderDateEntity

//create entity here
@Entity(
    tableName = ReminderDateInfo.REMINDER_DATE_TABLE_NANE,
    foreignKeys = [
        ForeignKey(
            entity = ReminderEntity::class,
            parentColumns = [ReminderInfo.ID_COLUMN_NAME],
            childColumns = [ReminderDateInfo.REMINDER_ID_COLUMN_NAME],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DayEntity::class,
            parentColumns = [DayInfo.ID_COLUMN_NAME],
            childColumns = [ReminderDateInfo.DAY_ID_COLUMN_NAME],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class ReminderDateEntity(
    @PrimaryKey @ColumnInfo(
        name = ReminderDateInfo.ID_COLUMN_NAME
    ) override val id: Long,
    @ColumnInfo(
        name = ReminderDateInfo.REMINDER_ID_COLUMN_NAME
    ) override val reminderId: Long,
    @ColumnInfo(
        name = ReminderDateInfo.DAY_ID_COLUMN_NAME
    ) override val dayId: Long,
    @ColumnInfo(
        name = ReminderDateInfo.CREATED_AT_COLUMN_NAME
    ) override val createdAt: Long
) : IReminderDateEntity




