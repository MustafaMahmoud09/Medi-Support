package com.example.bmi.domain.usecase.declarations

import com.example.bmi.domain.model.ChartBMIModel
import kotlinx.coroutines.flow.Flow

interface IGetLastWeekBMIRecordsUseCase {

    suspend operator fun invoke(): Flow<List<ChartBMIModel>>

}//end IGetLastWeekBMIRecordsUseCase