package com.example.reminder.domain.usecase.interfaces

import com.example.reminder.domaim.domain.model.Day
import kotlinx.coroutines.flow.Flow

interface IGetDaysUseCase {

    suspend operator fun invoke(): Flow<List<Day>>

}//end IGetDaysUseCase