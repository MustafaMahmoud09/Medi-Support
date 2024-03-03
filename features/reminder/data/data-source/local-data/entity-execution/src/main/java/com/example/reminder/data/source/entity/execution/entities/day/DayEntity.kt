package com.example.reminder.data.source.entity.execution.entities.day

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.reminder.domain.entity.interfaces.IDayEntity

@Entity(tableName = DayInfo.DAY_TABLE_NAME)
data class DayEntity(
    @PrimaryKey(
        autoGenerate = true
    ) @ColumnInfo(
        name = DayInfo.ID_COLUMN_NAME
    ) override val id: Long = 0,
    @ColumnInfo(
        name = DayInfo.DAY_COLUMN_NAME
    ) override val day: String,
    @ColumnInfo(
        name = DayInfo.CREATED_AT_COLUMN_NAME
    ) override val createdAt: Long = System.currentTimeMillis(),
) : IDayEntity
