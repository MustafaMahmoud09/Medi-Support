package com.example.reminder.domain.usecase

import com.example.reminder.domain.usecase.interfaces.ICalculateDifferentDaysUseCase
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class CalculateDifferentDaysUseCase : ICalculateDifferentDaysUseCase {

    //function for calculate different days between today and reminder days
    override fun invoke(reminderTime: LocalTime, dayNumber: Long): Int {

        //get current day
//        val today = LocalDate.now().dayOfWeek
//        val ordinal = today.value
        val ordinal = provideCurrentDay()

        //0 - reminder day number - 6
        //to day have number id
        //calculate different between today and reminder number
        val result = if (dayNumber.toInt() - 1 > ordinal) {

            if (
                LocalTime.now().hour < reminderTime.hour ||
                (LocalTime.now().hour == reminderTime.hour &&
                        LocalTime.now().minute < reminderTime.minute) ||
                (LocalTime.now().hour == reminderTime.hour &&
                        LocalTime.now().minute == reminderTime.minute &&
                        LocalTime.now().second <= reminderTime.second)
            ) {
                dayNumber.toInt() - ordinal - 1
            } else {
                dayNumber.toInt() - ordinal - 2
            }//end else

        }//end if
        else if (dayNumber.toInt() - 1 < ordinal) {

            if (
                LocalTime.now().hour < reminderTime.hour ||
                (LocalTime.now().hour == reminderTime.hour &&
                        LocalTime.now().minute < reminderTime.minute) ||
                (LocalTime.now().hour == reminderTime.hour &&
                        LocalTime.now().minute == reminderTime.minute &&
                        LocalTime.now().second <= reminderTime.second)
            ) {
                6 - ordinal + dayNumber.toInt()
            } else {
                6 - ordinal + dayNumber.toInt() - 1
            }//end else

        }//end else
        else {

            if (
                LocalTime.now().hour < reminderTime.hour ||
                (LocalTime.now().hour == reminderTime.hour &&
                        LocalTime.now().minute < reminderTime.minute) ||
                (LocalTime.now().hour == reminderTime.hour &&
                        LocalTime.now().minute == reminderTime.minute &&
                        LocalTime.now().second <= reminderTime.second)
            ) {
                0
            }//end if
            else {
                6
            }
        }//end else

        return result

    }//end invoke


    override fun provideCurrentDay(): Int {

        return when (LocalDate.now().dayOfWeek.name) {
            DayOfWeek.SUNDAY.name -> {
                0
            }

            DayOfWeek.MONDAY.name -> {
                1
            }

            DayOfWeek.TUESDAY.name -> {
                2
            }

            DayOfWeek.WEDNESDAY.name -> {
                3
            }

            DayOfWeek.THURSDAY.name -> {
                4
            }

            DayOfWeek.FRIDAY.name -> {
                5
            }

            DayOfWeek.SATURDAY.name -> {
                6
            }

            else -> {
                -10
            }
        }

    }//end provideCurrentDay

}//end CalculateDifferentDaysUseCase