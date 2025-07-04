package com.example.reminder.data.local.data.data.access.`object`

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.reminder.data.source.entity.execution.complexQuery.NearestReminder
import com.example.reminder.data.source.entity.execution.complexQuery.ReminderWithDays
import com.example.reminder.data.source.entity.execution.entities.day.DayInfo
import com.example.reminder.data.source.entity.execution.entities.reminder.ReminderEntity
import com.example.reminder.data.source.entity.execution.entities.reminder.ReminderInfo
import com.example.reminder.data.source.entity.execution.entities.reminder_date.ReminderDateInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalTime

@Dao
interface ReminderDao {

    //TODO:: Function For Insert Reminder Row
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reminder: ReminderEntity): Long


    //TODO:: Function For Delete Reminder Row
    @Query(
        "DELETE FROM ${ReminderInfo.REMINDER_TABLE_NAME}" +
                " WHERE ${ReminderInfo.ID_COLUMN_NAME} = :id "
    )
    suspend fun delete(id: Long)


    //TODO:: Function For Select All Reminders
    @Transaction
    @Query(
        "SELECT * FROM ${ReminderInfo.REMINDER_TABLE_NAME} " +
                "ORDER BY ${ReminderInfo.TIME_COLUMN_NAME} ASC " +
                "LIMIT :pageSize " +
                "OFFSET ((:page - 1) * :pageSize)"
    )
    suspend fun select(pageSize: Int, page: Int): List<ReminderWithDays>


    //TODO:: Function For Update Reminder Status
    @Query(
        "UPDATE ${ReminderInfo.REMINDER_TABLE_NAME} SET " +
                "${ReminderInfo.STATUS_COLUMN_NAME} = :status " +
                "WHERE ${ReminderInfo.ID_COLUMN_NAME} = :id"
    )
    suspend fun update(id: Long, status: Boolean,)


    //TODO:: Function For Get Nearest Reminder
    @Query(
        "SELECT reminder.${
            ReminderInfo.ID_COLUMN_NAME
        } AS id,reminder.${
            ReminderInfo.NAME_COLUMN_NAME
        } AS name,reminder.${
            ReminderInfo.TIME_COLUMN_NAME
        } AS time,day.${
            DayInfo.DAY_COLUMN_NAME
        } AS day, " +
                "day.${
                    DayInfo.ID_COLUMN_NAME
                } AS dayId, " +
                "CASE " +
                "WHEN day.${
                    DayInfo.ID_COLUMN_NAME
                } - :currentDayNumber  - 1 > 0 OR(" +
                " day.${
                    DayInfo.ID_COLUMN_NAME
                } - :currentDayNumber  - 1 == 0 AND :localTime <= reminder.${
                    ReminderInfo.TIME_COLUMN_NAME
                }) THEN " +
                "day.${
                    DayInfo.ID_COLUMN_NAME
                } - :currentDayNumber - 1 " +
                "ELSE 6 - :currentDayNumber + day.${
                    DayInfo.ID_COLUMN_NAME
                }  END AS endResultDaysDifferent " +
                "FROM ${
                    ReminderInfo.REMINDER_TABLE_NAME
                } AS reminder INNER JOIN ${
                    ReminderDateInfo.REMINDER_DATE_TABLE_NANE
                } AS reminder_date ON reminder.${
                    ReminderInfo.ID_COLUMN_NAME
                } = reminder_date.${
                    ReminderDateInfo.REMINDER_ID_COLUMN_NAME
                } INNER JOIN ${
                    DayInfo.DAY_TABLE_NAME
                } AS day ON reminder_date.${
                    ReminderDateInfo.DAY_ID_COLUMN_NAME
                } = day.${
                    DayInfo.ID_COLUMN_NAME
                } WHERE reminder.${
                    ReminderInfo.STATUS_COLUMN_NAME
                } = :status " +
                "ORDER BY endResultdaysDifferent ASC," +
                "reminder.${
                    ReminderInfo.TIME_COLUMN_NAME
                } ASC " +
                "LIMIT 1"
    )
    fun nearestReminder(
        status: Boolean,
        localTime: LocalTime,
        currentDayNumber: Int
    ): Flow<List<NearestReminder>>


    //TODO:: Function For Get Reminders By Status it
    @Query(
        "SELECT * FROM ${
            ReminderInfo.REMINDER_TABLE_NAME
        } WHERE ${
            ReminderInfo.STATUS_COLUMN_NAME
        } = :status "
    )
    fun selectRemindersByStatus(
        status: Boolean
    ): Flow<List<ReminderEntity>>

}//end ReminderDao
