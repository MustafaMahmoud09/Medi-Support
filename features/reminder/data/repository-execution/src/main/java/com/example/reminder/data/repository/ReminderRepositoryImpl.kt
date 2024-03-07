package com.example.reminder.data.repository

import com.example.localdata.MediSupportDatabase
import com.example.reminder.data.source.entity.execution.entities.day.DayEntity
import com.example.reminder.data.source.entity.execution.entities.reminder.ReminderEntity
import com.example.reminder.data.source.entity.execution.entities.reminder_date.ReminderDateEntity
import com.example.reminder.data.source.shared.preferences.ReminderPreferencesAccess
import com.example.reminder.domain.entity.interfaces.complexQuery.INearestReminder
import com.example.reminder.domain.entity.interfaces.entity.IDayEntity
import com.example.reminder.domain.entity.interfaces.complexQuery.IReminderWithDays
import com.example.repository.interfaces.IReminderRepository
import com.example.shared.entity.implementation.user.UserEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalTime

class ReminderRepositoryImpl(
    private val reminderPreferencesAccess: ReminderPreferencesAccess,
    private val localDatabase: MediSupportDatabase
) : IReminderRepository {//end RepositoryImp

    //fun return false if used the reminder feature before else return true
    override fun getRunReminderFeature(): Boolean {

        return reminderPreferencesAccess.runReminderManager().getRunReminder()

    }//end getRunReminderFeature

    //fun for set use reminder feature
    override fun setRunReminderFeature(value: Boolean) {

        reminderPreferencesAccess.runReminderManager().setRunReminder(
            value = value
        )

    }//end setRunReminderFeature


    //fun for store week days
    override suspend fun storeWeekDays(days: List<String>) {

        //loop on days to store it in room database
        days.forEach { day ->

            //create day row here
            localDatabase.dayDao().insert(
                day = DayEntity(
                    day = day
                )
            )

        }//end forEach

        localDatabase.userDao().insert(
            user = UserEntity(
                email = "mustafa@gmail.com",
                password = "12345678",
                auth = true
            )
        )

    }//end storeWeekDays

    //fun for select week days
    override suspend fun getWeekDays(): Flow<List<IDayEntity>> {

        return localDatabase.dayDao().select()

    }//end getWeekDays

    //function for store reminder
    override suspend fun storeReminder(
        name: String,
        userId: Long,
        time: LocalTime,
        days: List<Long>
    ) {

        //store reminder here
        val reminderId = localDatabase.reminderDao().insert(
            reminder = ReminderEntity(
                name = name,
                time = time,
                status = true,
                userId = userId,
            )
        )

        //for loop on days here
        days.forEach {

            //create reminder date here
            localDatabase.reminderDateDao().insert(
                reminderDay = ReminderDateEntity(
                    reminderId = reminderId,
                    dayId = it
                )
            )

        }//end forEach

    }//end storeReminder

    //function for get reminders from local database
    override suspend fun getReminders(userId: Long): Flow<List<IReminderWithDays>> {

        return localDatabase.reminderDao().select(
            userId = userId
        )

    }//end getReminders

    //function for provide nearest reminder
    override suspend fun getNearestReminder(status: Boolean): Flow<List<INearestReminder>> {

        return localDatabase
            .reminderDao()
            .nearestReminder(
                status = status
            )

    }//end getNearestReminder

    //function for delete reminder
    override suspend fun deleteReminder(id: Long, userId: Long) {

        localDatabase.reminderDao().delete(
            id = id,
            userId = userId
        )

    }//end deleteReminder

    //function for update reminder status
    override suspend fun updateReminderStatus(reminderId: Long, newValue: Boolean, userId: Long) {

        localDatabase.reminderDao().update(
            id = reminderId,
            status = newValue,
            userId = userId,
        )

    }//end updateReminderStatus

}