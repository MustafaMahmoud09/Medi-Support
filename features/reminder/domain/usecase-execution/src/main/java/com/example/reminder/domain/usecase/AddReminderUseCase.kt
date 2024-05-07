package com.example.reminder.domain.usecase

import com.example.reminder.domaim.domain.model.reminder.ReminderStoreData
import com.example.reminder.domain.usecase.interfaces.IAddReminderUseCase
import com.example.repository.interfaces.IReminderRepository

class AddReminderUseCase(
    private val reminderRepository: IReminderRepository
) : IAddReminderUseCase {

    //function for add reminder
    override suspend fun invoke(reminder: ReminderStoreData) {

        reminderRepository.storeReminder(
            name = reminder.reminderName,
            time = reminder.time,
            days = reminder.days,
        )

    }//end invoke

}//end AddReminderUseCase