package com.example.reminder.domain.usecase.interfaces

import com.example.reminder.domaim.domain.model.reminder.NearestReminderPresentationModel
import kotlinx.coroutines.flow.Flow

interface IGetNearestRemindersUseCase {

    suspend operator fun invoke(status: Boolean): Flow<List<NearestReminderPresentationModel>>

}//end IGetNearestActiveRemindersUseCase