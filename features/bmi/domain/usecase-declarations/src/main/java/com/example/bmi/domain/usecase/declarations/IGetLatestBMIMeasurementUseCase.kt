package com.example.bmi.domain.usecase.declarations

import com.example.bmi.domain.model.AdviceBMIModel
import kotlinx.coroutines.flow.Flow

interface IGetLatestBMIMeasurementUseCase {

    suspend operator fun invoke(): Flow<List<AdviceBMIModel>>

}//end IGetLatestBMIMeasurementUseCase