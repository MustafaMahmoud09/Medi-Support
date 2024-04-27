package com.example.blood.sugar.domain.repository.declarations

import com.example.blood.sugar.domain.dto.declarations.status.IBloodSugarStatusResponseDto
import com.example.blood.sugar.domain.entity.declarations.IBloodSugarEntity
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import kotlinx.coroutines.flow.Flow

interface IBloodSugarRepository {

    suspend fun createNewBloodSugarRecord(
        level: Float,
        statusId: Int
    ): Flow<Status<EffectResponse<Any>>>


    suspend fun getBloodSugarStatus()
            : Flow<Status<EffectResponse<IBloodSugarStatusResponseDto>>>


    suspend fun getLastWeekMeasurement()
            : Flow<List<IBloodSugarEntity>>


    suspend fun getLatestMeasurements(
        numberOfMeasurement: Long
    ): Flow<List<IBloodSugarEntity>>


    suspend fun getPageMeasurements(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IBloodSugarEntity>>

}//end IBloodSugarRepository