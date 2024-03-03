package com.example.reminder.data.repository

import com.example.localdata.MediSupportDatabase
import com.example.reminder.data.source.entity.execution.entities.day.DayEntity
import com.example.reminder.data.source.shared.preferences.ReminderPreferencesAccess
import com.example.reminder.domain.entity.interfaces.IDayEntity
import com.example.reminder.domain.entity.interfaces.IReminderWithDays
import com.example.repository.interfaces.IReminderRepository
import kotlinx.coroutines.flow.Flow
import java.sql.Date

class ReminderRepositoryImpl(
    private val reminderPreferencesAccess: ReminderPreferencesAccess,
    private val localDatabase: MediSupportDatabase
) : IReminderRepository {

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

    }//end storeWeekDays

    //fun for select week days
    override suspend fun getWeekDays(): Flow<List<IDayEntity>> {

        return localDatabase.dayDao().select()

    }//end getWeekDays

    override fun storeReminder(name: String, time: Date, days: List<Int>) {
        TODO("Not yet implemented")
    }

    override fun getReminders(): Flow<IReminderWithDays> {
        TODO("Not yet implemented")
    }

    override fun deleteReminder(id: Long) {
        TODO("Not yet implemented")
    }

    override fun updateReminderStatus(newValue: Boolean) {
        TODO("Not yet implemented")
    }

}//end RepositoryImp