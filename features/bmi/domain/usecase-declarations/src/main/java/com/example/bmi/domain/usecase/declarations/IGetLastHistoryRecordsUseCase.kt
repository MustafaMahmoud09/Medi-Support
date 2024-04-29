package com.example.bmi.domain.usecase.declarations

import com.example.bmi.domain.model.SimpleBMIModel
import kotlinx.coroutines.flow.Flow

interface IGetLastHistoryRecordsUseCase {

    suspend operator fun invoke(): Flow<List<SimpleBMIModel>>

}//end IGetLastHistoryRecordsUseCase