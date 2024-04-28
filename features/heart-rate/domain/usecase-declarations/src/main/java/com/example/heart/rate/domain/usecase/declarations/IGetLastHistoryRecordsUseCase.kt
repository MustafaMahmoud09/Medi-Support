package com.example.heart.rate.domain.usecase.declarations

import com.example.heart.rate.domain.domain.model.SimpleHeartRateModel
import kotlinx.coroutines.flow.Flow

interface IGetLastHistoryRecordsUseCase {

    suspend operator fun invoke(): Flow<List<SimpleHeartRateModel>>

}//end IGetLastHistoryRecordsUseCase