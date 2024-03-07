package com.example.localdata.dao.reminder

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.reminder.data.source.entity.execution.complexQuery.NearestReminder
import com.example.reminder.data.source.entity.execution.entities.day.DayEntity
import com.example.reminder.data.source.entity.execution.entities.day.DayInfo
import com.example.reminder.data.source.entity.execution.entities.reminder.ReminderInfo
import com.example.reminder.data.source.entity.execution.entities.reminder_date.ReminderDateInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface DayDao {

    //TODO:: Function For Insert Days
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(day: DayEntity)


    //TODO:: Function For Select Days
    @Query("SELECT * FROM ${DayInfo.DAY_TABLE_NAME}")
    fun select(): Flow<List<DayEntity>>

}//end ReminderDayDao
