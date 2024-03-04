package com.example.reminder.domain.usecase.interfaces

import com.example.reminder.domaim.domain.model.reminder.ReminderStoreData
import java.time.LocalTime

interface IAddReminderUseCase {

    suspend operator fun invoke(reminder: ReminderStoreData)

}//end IAddReminderUseCase