package com.example.localdata.convertors

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalTime

class Convertors {

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun localTimeToLong(localTime: LocalTime?): Long? {

        return localTime?.toNanoOfDay()

    }//end localTimeToLong

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun longToLocalTime(value: Long?): LocalTime? {

        return value?.let {
            LocalTime.ofNanoOfDay(it)
        }

    }//end longToLocalTime

}//end LocalTimeToLongConvertor
