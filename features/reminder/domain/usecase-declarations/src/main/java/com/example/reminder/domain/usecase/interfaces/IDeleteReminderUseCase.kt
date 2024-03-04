package com.example.reminder.domain.usecase.interfaces

interface IDeleteReminderUseCase {

    suspend operator fun invoke(reminderId: Long)

}//end IDeleteReminderUseCase