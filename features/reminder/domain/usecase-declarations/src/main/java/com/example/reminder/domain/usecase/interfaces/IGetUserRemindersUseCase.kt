package com.example.reminder.domain.usecase.interfaces

import com.example.reminder.domaim.domain.model.reminder.ReminderPresentationModel
import kotlinx.coroutines.flow.Flow

interface IGetUserRemindersUseCase {

    suspend operator fun invoke(): Flow<List<ReminderPresentationModel>>

}//end IGetUserRemindersUseCase