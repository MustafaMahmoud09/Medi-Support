package com.example.database_creator.convertors

import androidx.room.TypeConverter
import java.time.LocalTime

class Convertors {

    @TypeConverter
    fun localTimeToLong(localTime: LocalTime?): Long? {

        return localTime?.toNanoOfDay()

    }//end localTimeToLong

    @TypeConverter
    fun longToLocalTime(value: Long?): LocalTime? {

        return value?.let {
            LocalTime.ofNanoOfDay(it)
        }

    }//end longToLocalTime

}//end LocalTimeToLongConvertor