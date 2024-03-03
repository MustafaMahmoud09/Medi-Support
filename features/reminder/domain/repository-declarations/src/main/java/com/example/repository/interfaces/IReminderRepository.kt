package com.example.repository.interfaces

import com.example.reminder.domain.entity.interfaces.IDayEntity
import com.example.reminder.domain.entity.interfaces.IReminderWithDays
import kotlinx.coroutines.flow.Flow
import java.sql.Date

interface IReminderRepository {

    fun getRunReminderFeature(): Boolean

    fun setRunReminderFeature(value: Boolean)

    suspend fun storeWeekDays(days: List<String>)

    suspend fun getWeekDays(): Flow<List<IDayEntity>>

    fun storeReminder(name: String, time: Date, days: List<Int>)

    fun getReminders(): Flow<IReminderWithDays>

    fun deleteReminder(id: Long)

    fun updateReminderStatus(newValue: Boolean)

}//end IReminderRepository