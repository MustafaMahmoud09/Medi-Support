package com.example.reminder.domain.usecase

import com.example.reminder.domain.usecase.interfaces.ICalculateDifferentDaysUseCase
import java.time.LocalDate
import java.time.LocalTime

class CalculateDifferentDaysUseCase : ICalculateDifferentDaysUseCase {

    //function for calculate different days between today and reminder days
    override fun invoke(reminderTime: LocalTime, dayNumber: Long): Int {

        //get current day
        val today = LocalDate.now().dayOfWeek

        val ordinal = today.value

        //0 - reminder day number - 6
        //to day have number id
        //calculate different between today and reminder number
        val result = if (dayNumber.toInt() - 1 > ordinal) {

            if (LocalTime.now().second < reminderTime.second) {
                dayNumber.toInt() - ordinal - 1
            } else {
                dayNumber.toInt() - ordinal - 2
            }//end else

        }//end if
        else if (dayNumber.toInt() - 1 < ordinal) {

            if (LocalTime.now().second < reminderTime.second) {
                6 - ordinal + dayNumber.toInt()
            } else {
                6 - ordinal + dayNumber.toInt() - 1
            }//end else

        }//end else
        else {

            if (LocalTime.now().second >= reminderTime.second) {
                0
            }//end if
            else {
                6
            }
        }//end else

        return result

    }//end invoke

}//end CalculateDifferentDaysUseCase