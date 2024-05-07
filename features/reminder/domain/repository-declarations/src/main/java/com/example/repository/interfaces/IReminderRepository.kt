package com.example.repository.interfaces

import com.example.reminder.domain.entity.interfaces.complexQuery.INearestReminder
import com.example.reminder.domain.entity.interfaces.entity.IDayEntity
import com.example.reminder.domain.entity.interfaces.complexQuery.IReminderWithDays
import com.example.reminder.domain.entity.interfaces.entity.IReminderEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalTime

interface IReminderRepository {

    fun getRunningReminderFeatureState(): Boolean

    fun setRunningReminderFeatureState(value: Boolean)

    fun setReminderServiceState(value: Boolean)

    fun getReminderServiceState(): Boolean

    suspend fun storeWeekDays(days: List<String>)

    suspend fun getWeekDays(): Flow<List<IDayEntity>>

    suspend fun storeReminder(
        name: String,
        time: LocalTime, days: List<Long>
    )

    suspend fun getReminders(
        pageSize: Int,
        page: Int
    ): List<IReminderWithDays>

    suspend fun getNearestReminder(
        status: Boolean,
        localTime: LocalTime,
    ): Flow<List<INearestReminder>>

    suspend fun getRemindersByStatus(
        status: Boolean
    ): Flow<List<IReminderEntity>>

    suspend fun deleteReminder(id: Long)

    suspend fun updateReminderStatus(
        reminderId: Long,
        newValue: Boolean
    )


}//end IReminderRepository