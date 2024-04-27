package com.example.blood.sugar.domain.usecase.declarations

import com.example.blood.sugar.domain.model.SimpleBloodSugarModel
import kotlinx.coroutines.flow.Flow

interface IGetLastHistoryRecordsUseCase {

    suspend operator fun invoke(): Flow<List<SimpleBloodSugarModel>>

}//end IGetLastHistoryRecordsUseCase