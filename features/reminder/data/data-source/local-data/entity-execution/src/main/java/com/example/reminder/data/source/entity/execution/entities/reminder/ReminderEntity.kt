package com.example.reminder.data.source.entity.execution.entities.reminder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.reminder.domain.entity.interfaces.IReminderEntity
import com.example.shared.entity.implementation.user.UserEntity
import com.example.shared.entity.implementation.user.UserInfo

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
    @PrimaryKey @ColumnInfo(
        name = ReminderInfo.ID_COLUMN_NAME
    ) override val id: Long,
    @ColumnInfo(
        name = ReminderInfo.TIME_COLUMN_NAME
    ) override val time: Long,
    @ColumnInfo(
        name = ReminderInfo.STATUS_COLUMN_NAME
    ) override val status: Boolean,
    @ColumnInfo(
        name = ReminderInfo.USER_ID_COLUMN_NAME
    ) override val userId: Long,
    @ColumnInfo(
        name = ReminderInfo.CREATED_AT_COLUMN_NAME
    ) override val createdAt: Long
) : IReminderEntity
