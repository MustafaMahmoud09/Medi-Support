package com.example.database_creator

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.article.data.source.local.data.access.`object`.ArticleDao
import com.example.article.data.source.local.entity.execution.entities.article.ArticleEntity
import com.example.blood.pressure.data.source.local.data.data.access.`object`.BloodPressureDao
import com.example.blood.pressure.data.source.local.data.entity.execution.bloodPressure.BloodPressureEntity
import com.example.blood.sugar.data.source.data.access.`object`.BloodSugarDao
import com.example.blood.sugar.data.source.local.data.entity.execution.bloodSugar.BloodSugarEntity
import com.example.database_creator.convertors.Convertors
import com.example.database_creator.dao.UserDao
import com.example.heart.rate.data.source.data.access.`object`.HeartRateDao
import com.example.heart.rate.data.source.entity.execution.heartRate.HeartRateEntity
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserEntity
import com.example.reminder.data.local.data.data.access.`object`.DayDao
import com.example.reminder.data.local.data.data.access.`object`.ReminderDao
import com.example.reminder.data.local.data.data.access.`object`.ReminderDateDao
import com.example.reminder.data.source.entity.execution.entities.day.DayEntity
import com.example.reminder.data.source.entity.execution.entities.reminder.ReminderEntity
import com.example.reminder.data.source.entity.execution.entities.reminder_date.ReminderDateEntity


@Database(
    entities = [
        UserEntity::class,
        ReminderEntity::class,
        DayEntity::class,
        ReminderDateEntity::class,
        ArticleEntity::class,
        BloodPressureEntity::class,
        BloodSugarEntity::class,
        HeartRateEntity::class
    ],
    version = 3
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


    /**
     *abstract function for provide article dao
     *
     * @return ArticleDao
     **/
    abstract fun articleDao(): ArticleDao


    /**
     *abstract function for provide blood pressure dao
     *
     * @return BloodPressureDao
     **/
    abstract fun bloodPressureDao(): BloodPressureDao


    /**
     *abstract function for provide blood sugar dao
     *
     * @return BloodPressureDao
     **/
    abstract fun bloodSugarDao(): BloodSugarDao


    /**
     *abstract function for provide heart rate dao
     *
     * @return HeartRateDao
     **/
    abstract fun heartRateDao(): HeartRateDao

}//end MediSupportDatabase