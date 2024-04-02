package com.example.reminder.domain.usecase.interfaces

import java.time.LocalTime

interface ICalculateDifferentDaysUseCase {

    operator fun invoke(reminderTime: LocalTime, dayNumber: Long): Int

}//end ICalculateDifferentDaysUseCase