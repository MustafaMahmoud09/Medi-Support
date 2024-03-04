package com.example.repository.interfaces

import com.example.reminder.domain.entity.interfaces.IDayEntity
import com.example.reminder.domain.entity.interfaces.IReminderWithDays
import kotlinx.coroutines.flow.Flow
import java.time.LocalTime

interface IReminderRepository {

    fun getRunReminderFeature(): Boolean

    fun setRunReminderFeature(value: Boolean)

    suspend fun storeWeekDays(days: List<String>)

    suspend fun getWeekDays(): Flow<List<IDayEntity>>

    suspend fun storeReminder(name: String, userId: Long, time: LocalTime, days: List<Long>)

    suspend fun getReminders(userId: Long): Flow<List<IReminderWithDays>>

    suspend fun deleteReminder(id: Long, userId: Long)

    suspend fun updateReminderStatus(reminderId: Long, newValue: Boolean, userId: Long)

}//end IReminderRepository