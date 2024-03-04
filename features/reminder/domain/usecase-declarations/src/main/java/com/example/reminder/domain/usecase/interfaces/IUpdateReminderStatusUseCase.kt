package com.example.reminder.domain.usecase.interfaces

import com.example.reminder.domaim.domain.model.reminder.ReminderUpdateData

interface IUpdateReminderStatusUseCase {

     suspend operator fun invoke(reminder: ReminderUpdateData)

}//end IGetUserReminderUseCase