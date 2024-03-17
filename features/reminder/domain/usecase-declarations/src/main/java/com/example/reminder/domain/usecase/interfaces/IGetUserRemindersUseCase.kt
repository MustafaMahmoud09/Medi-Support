package com.example.reminder.domain.usecase.interfaces

import com.example.reminder.domaim.domain.model.reminder.ReminderPresentationModel
import kotlinx.coroutines.flow.Flow

interface IGetUserRemindersUseCase {

    suspend operator fun invoke(
        page: Int,
        pageSize: Int
    ): List<ReminderPresentationModel>

}//end IGetUserRemindersUseCase