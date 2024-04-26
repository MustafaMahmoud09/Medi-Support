package com.example.blood.pressure.domain.usecase.declarations

import com.example.blood.pressure.domain.model.SimpleBloodPressureModel
import kotlinx.coroutines.flow.Flow

interface IGetLastHistoryRecordsUseCase {

    suspend operator fun invoke(): Flow<List<SimpleBloodPressureModel>>

}//end IGetLastHistoryRecordsUseCase