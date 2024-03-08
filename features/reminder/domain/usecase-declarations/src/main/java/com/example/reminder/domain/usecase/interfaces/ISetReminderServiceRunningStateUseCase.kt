package com.example.reminder.domain.usecase.interfaces

interface ISetReminderServiceRunningStateUseCase {

    operator fun invoke(status: Boolean)

}//end ISetReminderServiceRunningStateUseCase