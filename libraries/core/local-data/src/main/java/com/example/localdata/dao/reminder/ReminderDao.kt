package com.example.localdata.dao.reminder

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.reminder.data.source.entity.execution.complexQuery.ReminderWithDays
import com.example.reminder.data.source.entity.execution.entities.reminder.ReminderEntity
import com.example.reminder.data.source.entity.execution.entities.reminder.ReminderInfo
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

}//end ReminderDao