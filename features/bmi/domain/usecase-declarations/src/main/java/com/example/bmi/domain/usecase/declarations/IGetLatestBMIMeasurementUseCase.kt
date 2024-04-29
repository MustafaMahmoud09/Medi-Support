package com.example.heart.rate.domain.usecase.declarations

import com.example.heart.rate.domain.domain.model.AdviceHeartRateModel
import kotlinx.coroutines.flow.Flow

interface IGetLatestHeartRateMeasurementUseCase {

    suspend operator fun invoke(): Flow<List<AdviceHeartRateModel>>

}//end IGetLatestHeartRateMeasurementUseCase