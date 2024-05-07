package com.example.reminder.data.repository

import com.example.database_creator.MediSupportDatabase
import com.example.reminder.data.source.entity.execution.entities.day.DayEntity
import com.example.reminder.data.source.entity.execution.entities.reminder.ReminderEntity
import com.example.reminder.data.source.entity.execution.entities.reminder_date.ReminderDateEntity
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import com.example.reminder.domain.entity.interfaces.complexQuery.INearestReminder
import com.example.reminder.domain.entity.interfaces.entity.IDayEntity
import com.example.reminder.domain.entity.interfaces.complexQuery.IReminderWithDays
import com.example.reminder.domain.entity.interfaces.entity.IReminderEntity
import com.example.repository.interfaces.IReminderRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalTime

class ReminderRepositoryImpl(
    private val sharedPreferencesAccessObject: SharedPreferencesAccessObject,
    private val localDatabase: MediSupportDatabase
) : IReminderRepository {

    //fun return false if used the reminder feature before else return true
    override fun getRunningReminderFeatureState(): Boolean {

        return sharedPreferencesAccessObject
            .runningReminderManager()
            .getRunningReminderFeatureState()

    }//end getRunReminderFeature

    //fun for set use reminder feature
    override fun setRunningReminderFeatureState(value: Boolean) {

        sharedPreferencesAccessObject
            .runningReminderManager()
            .setRunningReminderFeatureState(
                value = value
            )

    }//end setRunReminderFeature


    //function for set reminder service state
    override fun setReminderServiceState(value: Boolean) {

        sharedPreferencesAccessObject
            .runningReminderServiceManager()
            .setReminderServiceState(
                value = value
            )

    }//end setReminderServiceState

    //function for get reminder service state
    override fun getReminderServiceState(): Boolean {

        return sharedPreferencesAccessObject
            .runningReminderServiceManager()
            .getReminderServiceState()

    }//end getReminderServiceState


    //fun for store week days
    override suspend fun storeWeekDays(days: List<String>) {

        var dayId = 1L

        //loop on days to store it in room database
        days.forEach { day ->

            //create day row here
            localDatabase.dayDao().insert(
                day = DayEntity(
                    day = day,
                    id = dayId
                )
            )

            dayId += 1

        }//end forEach

    }//end storeWeekDays

    //fun for select week days
    override suspend fun getWeekDays(): Flow<List<IDayEntity>> {

        return localDatabase.dayDao().select()

    }//end getWeekDays


    //function for store reminder
    override suspend fun storeReminder(
        name: String,
        time: LocalTime,
        days: List<Long>
    ) {

        //store reminder here
        val reminderId = localDatabase.reminderDao().insert(
            reminder = ReminderEntity(
                name = name,
                time = time,
                status = true,
            )
        )

        //for loop on days here
        days.forEach { dayId ->

            //create reminder date here
            localDatabase.reminderDateDao().insert(
                reminderDay = ReminderDateEntity(
                    reminderId = reminderId,
                    dayId = dayId
                )
            )

        }//end forEach

    }//end storeReminder

    //function for get reminders from local database
    override suspend fun getReminders(
        pageSize: Int,
        page: Int
    ): List<IReminderWithDays> {

        return localDatabase.reminderDao().select(
            pageSize = pageSize,
            page = page
        )

    }//end getReminders

    override suspend fun getRemindersByStatus(
        status: Boolean,
    ): Flow<List<IReminderEntity>> {

        return localDatabase.reminderDao().selectRemindersByStatus(
            status = status,
        )

    }//end getRemindersByStatus

    //function for provide nearest reminder
    override suspend fun getNearestReminder(
        status: Boolean,
        localTime: LocalTime,
    ): Flow<List<INearestReminder>> {

        return localDatabase
            .reminderDao()
            .nearestReminder(
                status = status,
                localTime = localTime,
            )

    }//end getNearestReminder

    //function for delete reminder
    override suspend fun deleteReminder(
        id: Long,
    ) {

        localDatabase.reminderDao().delete(
            id = id,
        )

    }//end deleteReminder

    //function for update reminder status
    override suspend fun updateReminderStatus(
        reminderId: Long,
        newValue: Boolean,
    ) {

        localDatabase.reminderDao().update(
            id = reminderId,
            status = newValue,
        )

    }//end updateReminderStatus

}//end ReminderRepositoryImpl