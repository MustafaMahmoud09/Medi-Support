package com.example.reminder.domain.usecase

import com.example.reminder.domain.usecase.interfaces.ISetReminderServiceRunningStateUseCase
import com.example.repository.interfaces.IReminderRepository

class SetReminderServiceRunningStateUseCase(
    private val reminderRepository: IReminderRepository
) : ISetReminderServiceRunningStateUseCase {

    //function for change reminder service running state
    override fun invoke(status: Boolean) {

        reminderRepository.setReminderServiceState(
            value = status
        )

    }//end invoke

}//end SetReminderServiceRunningStateUseCase