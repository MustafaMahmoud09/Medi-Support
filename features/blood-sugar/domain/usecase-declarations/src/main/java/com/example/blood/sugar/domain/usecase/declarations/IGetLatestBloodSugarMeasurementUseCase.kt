package com.example.blood.sugar.domain.usecase.declarations

import com.example.blood.sugar.domain.model.AdviceBloodSugarModel
import kotlinx.coroutines.flow.Flow

interface IGetLatestBloodSugarMeasurementUseCase {

    suspend operator fun invoke(): Flow<List<AdviceBloodSugarModel>>

}//end IGetLatestBloodSugarMeasurementUseCase