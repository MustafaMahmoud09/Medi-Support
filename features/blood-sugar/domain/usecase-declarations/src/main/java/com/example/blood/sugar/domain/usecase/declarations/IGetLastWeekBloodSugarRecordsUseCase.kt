package com.example.blood.sugar.domain.usecase.declarations

import com.example.blood.sugar.domain.model.ChartBloodSugarModel
import kotlinx.coroutines.flow.Flow

interface IGetLastWeekBloodSugarRecordsUseCase {

    suspend operator fun invoke(): Flow<List<ChartBloodSugarModel>>

}//end IGetLastWeekBloodSugarRecordsUseCase