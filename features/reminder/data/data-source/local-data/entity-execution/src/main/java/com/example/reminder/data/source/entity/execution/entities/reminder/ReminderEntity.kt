package com.example.reminder.data.source.entity.execution.entities.reminder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.reminder.domain.entity.interfaces.entity.IReminderEntity
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserEntity
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo
import java.time.LocalTime

@Entity(
    tableName = ReminderInfo.REMINDER_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = [UserInfo.ID_COLUMN_NAME],
            childColumns = [ReminderInfo.USER_ID_COLUMN_NAME],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class ReminderEntity(
    @PrimaryKey(
        autoGenerate = true
    ) @ColumnInfo(
        name = ReminderInfo.ID_COLUMN_NAME
    ) override val id: Long = 0,
    @ColumnInfo(
        name = ReminderInfo.NAME_COLUMN_NAME
    ) override val name: String,
    @ColumnInfo(
        name = ReminderInfo.TIME_COLUMN_NAME
    ) override val time: LocalTime,
    @ColumnInfo(
        name = ReminderInfo.STATUS_COLUMN_NAME
    ) override val status: Boolean,
    @ColumnInfo(
        name = ReminderInfo.USER_ID_COLUMN_NAME
    ) override val userId: Long,
    @ColumnInfo(
        name = ReminderInfo.CREATED_AT_COLUMN_NAME
    ) override val createdAt: Long = System.currentTimeMillis()
) : IReminderEntity
