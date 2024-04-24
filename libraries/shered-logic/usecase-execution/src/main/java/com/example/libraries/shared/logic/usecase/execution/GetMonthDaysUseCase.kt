package com.example.libraries.shared.logic.usecase.execution

import com.example.libraries.shared.logic.domain.model.DayModel
import com.example.libraries.shered.logic.usecase.declarations.IGetMonthDaysUseCase
import java.time.LocalDate
import java.time.YearMonth

class GetMonthDaysUseCase : IGetMonthDaysUseCase {

    //function for provide current month days
    override fun invoke(): List<DayModel> {

        //for get current month and year
        val currentYear = YearMonth.now().year
        val currentMonth = YearMonth.now().monthValue

        //for get number of days in current month
        val yearMonth = YearMonth.of(currentYear, currentMonth)
        val daysInMonth = yearMonth.lengthOfMonth()
        val firstDayOfMonth = yearMonth.atDay(1)

        //for get current month days
        val monthDays = (0 until daysInMonth).map { firstDayOfMonth.plusDays(it.toLong()) }

        return monthDays.indices.map { count ->

            val dayName = if (monthDays[count].dayOfWeek.name.length > 3) {
                monthDays[count].dayOfWeek.name.substring(0, 3)
            } else {
                monthDays[count].dayOfWeek.name
            }

            //map month days from date to day model
            DayModel(
                name = if (dayName.length > 1) {
                    dayName.substring(0, 1).uppercase() +
                            dayName.substring(1).lowercase()
                } else {
                    dayName.uppercase()
                },
                number = monthDays[count].dayOfMonth,
                today = monthDays[count] == LocalDate.now()
            )

        }//end map

    }//end invoke

}//end GetMonthDaysUseCase