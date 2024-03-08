package com.example.reminder.domain.usecase.interfaces

import kotlinx.coroutines.flow.Flow

interface IGetActiveRemindersSizeUseCase {

    suspend operator fun invoke(): Flow<Long>

}//end IGetActiveRemindersSizeUseCase