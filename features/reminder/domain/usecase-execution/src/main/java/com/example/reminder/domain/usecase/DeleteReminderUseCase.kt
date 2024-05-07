package com.example.reminder.domain.usecase

import com.example.reminder.domain.usecase.interfaces.IDeleteReminderUseCase
import com.example.repository.interfaces.IReminderRepository

class DeleteReminderUseCase(
    private val reminderRepository: IReminderRepository
) : IDeleteReminderUseCase {

    //function for delete reminder
    override suspend fun invoke(reminderId: Long) {

        reminderRepository.deleteReminder(
            id = reminderId
        )

    }//end invoke

}//end DeleteReminderUseCase