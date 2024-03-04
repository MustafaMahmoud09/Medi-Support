package com.example.localdata


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.localdata.convertors.Convertors
import com.example.localdata.dao.UserDao
import com.example.localdata.dao.reminder.DayDao
import com.example.localdata.dao.reminder.ReminderDao
import com.example.localdata.dao.reminder.ReminderDateDao
import com.example.reminder.data.source.entity.execution.entities.day.DayEntity
import com.example.reminder.data.source.entity.execution.entities.reminder.ReminderEntity
import com.example.reminder.data.source.entity.execution.entities.reminder_date.ReminderDateEntity
import com.example.shared.entity.implementation.user.UserEntity


@Database(
    entities = [
        UserEntity::class,
        ReminderEntity::class,
        DayEntity::class,
        ReminderDateEntity::class
    ],
    version = 1
)
@TypeConverters(Convertors::class)
abstract class MediSupportDatabase : RoomDatabase() {

    /**
     *abstract function for provide reminder dao
     *
     * @return ReminderDao
     ***/
    abstract fun reminderDao(): ReminderDao


    /**
     *abstract function for provide day dao
     *
     * @return DayDao
     * */
    abstract fun dayDao(): DayDao


    /**
     *abstract function for provide reminder date dao
     *
     * @return ReminderDateDao
     * */
    abstract fun reminderDateDao(): ReminderDateDao


    /**
     *abstract function for provide user dao
     *
     * @return UserDao
     * */
    abstract fun userDao(): UserDao

}//end MediSupportDatabase