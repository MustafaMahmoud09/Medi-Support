package com.example.reminder.domain.usecase

import com.example.reminder.domain.usecase.interfaces.IGetReminderServiceRunningStateUseCase
import com.example.repository.interfaces.IReminderRepository

class GetReminderServiceRunningStateUseCase(
    private val reminderRepository: IReminderRepository
) : IGetReminderServiceRunningStateUseCase {

    //function for get reminder service running
    override fun invoke(): Boolean {

        return reminderRepository.getReminderServiceState()

    }//end invoke

}//end GetReminderServiceRunningStateUseCase