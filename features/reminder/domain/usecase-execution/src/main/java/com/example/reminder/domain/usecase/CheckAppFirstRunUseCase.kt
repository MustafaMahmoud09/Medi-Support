package com.example.reminder.domain.usecase

import com.example.reminder.domain.usecase.interfaces.ICheckAppFirstRunUseCase
import com.example.repository.interfaces.IReminderRepository

class CheckAppFirstRunUseCase(
    private val reminderRepository: IReminderRepository
) : ICheckAppFirstRunUseCase {

    //fun return false if used the reminder feature before else return true
    override fun invoke(): Boolean {

        return !reminderRepository.getRunningReminderFeatureState()

    }//end invoke

}//end CheckAppFirstRunUseCase