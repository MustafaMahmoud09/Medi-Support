package com.example.reminder.domain.usecase.interfaces

import com.example.reminder.domaim.domain.model.reminder.NearestReminderPresentationModel
import kotlinx.coroutines.flow.Flow
import java.time.LocalTime

interface IGetNearestRemindersUseCase {

    suspend operator fun invoke(
        status: Boolean,
        localTime: LocalTime,
        currentDayNumber: Int
    ): Flow<List<NearestReminderPresentationModel>>


}//end IGetNearestActiveRemindersUseCase