package com.example.heart.rate.domain.usecase.declarations

import com.example.heart.rate.domain.domain.model.ChartHeartRateModel
import kotlinx.coroutines.flow.Flow

interface IGetLastWeekHeartRateRecordsUseCase {

    suspend operator fun invoke(): Flow<List<ChartHeartRateModel>>

}//end IGetLastWeekHeartRateRecordsUseCase