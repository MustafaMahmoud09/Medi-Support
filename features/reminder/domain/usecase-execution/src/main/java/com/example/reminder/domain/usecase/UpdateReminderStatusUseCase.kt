package com.example.reminder.domain.usecase

import com.example.reminder.domaim.domain.model.reminder.ReminderUpdateData
import com.example.reminder.domain.usecase.interfaces.IUpdateReminderStatusUseCase
import com.example.repository.interfaces.IReminderRepository

class UpdateReminderStatusUseCase(
    private val reminderRepository: IReminderRepository
) : IUpdateReminderStatusUseCase {

    //function for update reminder status
    override suspend fun invoke(reminder: ReminderUpdateData) {

        reminderRepository.updateReminderStatus(
            reminderId = reminder.reminderId,
            newValue = reminder.status,
            userId = 1
        )

    }//end invoke

}//end IUpdateReminderStatusUseCase