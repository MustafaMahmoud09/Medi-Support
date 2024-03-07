package com.example.localdata.dao.reminder


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


@Dao
interface ReminderDao {

    //TODO:: Function For Insert Reminder Row
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reminder: ReminderEntity): Long


    //TODO:: Function For Delete Reminder Row
    @Query(
        "DELETE FROM ${ReminderInfo.REMINDER_TABLE_NAME}" +
                " WHERE ${ReminderInfo.ID_COLUMN_NAME} = :id " +
                "AND ${ReminderInfo.USER_ID_COLUMN_NAME} = :userId"
    )
    suspend fun delete(id: Long, userId: Long)


    //TODO:: Function For Select All Reminders
    @Transaction
    @Query(
        "SELECT * FROM ${ReminderInfo.REMINDER_TABLE_NAME}" +
                " WHERE ${ReminderInfo.USER_ID_COLUMN_NAME} = :userId"
    )
    fun select(userId: Long): Flow<List<ReminderWithDays>>


    //TODO:: Function For Update Reminder Status
    @Query(
        "UPDATE ${ReminderInfo.REMINDER_TABLE_NAME} SET " +
                "${ReminderInfo.STATUS_COLUMN_NAME} = :status " +
                "WHERE ${ReminderInfo.ID_COLUMN_NAME} = :id AND " +
                "${ReminderInfo.USER_ID_COLUMN_NAME} = :userId"
    )
    suspend fun update(id: Long, status: Boolean, userId: Long)


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
                "CASE WHEN CAST(strftime('%w', 'now') AS INTEGER) - day.${
                    DayInfo.ID_COLUMN_NAME
                } - 1 = 0 AND CAST(strftime('%H:%M:%S','now', 'localtime') AS LONG )> reminder.${
                    ReminderInfo.TIME_COLUMN_NAME
                } THEN 7 " +
                "WHEN CAST(strftime('%w', 'now') AS INTEGER) - day.${
                    DayInfo.ID_COLUMN_NAME
                } - 1 >= 0 THEN CAST(strftime('%w', 'now') AS INTEGER) - day.${
                    DayInfo.ID_COLUMN_NAME
                } - 1 " +
                "ELSE CAST(strftime('%w', 'now') AS INTEGER) - day.${
                    DayInfo.ID_COLUMN_NAME
                } - 1 + 7 END AS endResultDaysDifferent " +
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
    fun nearestReminder(status: Boolean): Flow<List<NearestReminder>>


}//end ReminderDao

//AND(" +
//"(CAST(strftime('%w', 'now') AS INTEGER) > day.${
//DayInfo.ID_COLUMN_NAME
//} - 1 ) OR ( CAST(strftime('%w', 'now') AS INTEGER) = day.${
//DayInfo.ID_COLUMN_NAME
//} - 1 AND" +
//" reminder.${
//ReminderInfo.TIME_COLUMN_NAME
//} > TIME('now')" +
//"  )" +
//") OR

//@Query(
//    "SELECT reminder.${ReminderInfo.ID_COLUMN_NAME} AS id," +
//            "reminder.${ReminderInfo.NAME_COLUMN_NAME} AS name," +
//            "reminder.${ReminderInfo.TIME_COLUMN_NAME} AS time," +
//            "day.${DayInfo.DAY_COLUMN_NAME} AS day " +
//            "FROM ${ReminderInfo.REMINDER_TABLE_NAME} AS reminder " +
//            "INNER JOIN ${ReminderDateInfo.REMINDER_DATE_TABLE_NANE} AS reminder_date ON " +
//            "reminder.${ReminderInfo.ID_COLUMN_NAME} = " +
//            "reminder_date.${ReminderDateInfo.REMINDER_ID_COLUMN_NAME} " +
//            "INNER JOIN ${DayInfo.DAY_TABLE_NAME} AS day ON " +
//            "reminder_date.${ReminderDateInfo.DAY_ID_COLUMN_NAME} = " +
//            "day.${DayInfo.ID_COLUMN_NAME} " +
//            "WHERE reminder.${ReminderInfo.STATUS_COLUMN_NAME} = :status "
//)