package com.example.database_creator.convertors

import androidx.room.TypeConverter
import java.time.LocalTime
import java.util.Date

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


    @TypeConverter
    fun dateToLong(date: Date?): Long? {

        return date?.time

    }//end dateToLong

    @TypeConverter
    fun longToDate(value: Long?): Date? {

        return value?.let {
            Date(it)
        }

    }//end longToDate


}//end LocalTimeToLongConvertor