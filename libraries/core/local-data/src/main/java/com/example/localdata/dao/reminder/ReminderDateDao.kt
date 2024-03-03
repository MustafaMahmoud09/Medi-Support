package com.example.localdata.dao.reminder

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.reminder.data.source.entity.execution.entities.reminder_date.ReminderDateEntity

@Dao
interface ReminderDateDao {

    //TODO:: Function For Insert Reminder Days
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reminderDays: List<ReminderDateEntity>)

}//end ReminderDayDao