package com.example.reminder.domain.entity.interfaces.complexQuery

import java.time.LocalTime

interface INearestReminder {

    val id: Long

    val name: String

    val time: LocalTime

    val day: String

    val endResultDaysDifferent: Int

    val dayId: Long

}//end INearestReminder