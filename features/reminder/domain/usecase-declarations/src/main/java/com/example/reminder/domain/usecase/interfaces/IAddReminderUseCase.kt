package com.example.reminder.domain.usecase.interfaces

import com.example.reminder.domaim.domain.model.reminder.ReminderStoreData

interface IAddReminderUseCase {

    suspend operator fun invoke(reminder: ReminderStoreData)

}//end IAddReminderUseCase