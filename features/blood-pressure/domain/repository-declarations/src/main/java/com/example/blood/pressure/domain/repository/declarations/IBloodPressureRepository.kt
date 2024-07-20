package com.example.blood.pressure.domain.repository.declarations

import com.example.blood.pressure.domain.dto.declarations.descMeasurement.IDescBloodPressureResponseDto
import com.example.blood.pressure.domain.entity.declarations.IBloodPressureEntity
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import kotlinx.coroutines.flow.Flow

interface IBloodPressureRepository {

    suspend fun createNewBloodPressureRecord(
        systolic: Int,
        diastolic: Int
    ): Flow<Status<EffectResponse<Any>>>

    suspend fun getPageBloodPressure(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IBloodPressureEntity>>

    suspend fun getLatestBloodPressureRecord()
            : Flow<List<IBloodPressureEntity>>


    suspend fun getLatestHistoryBloodPressureRecords()
            : Flow<List<IBloodPressureEntity>>

    suspend fun getSystolicMeasurements()
            : Flow<Status<EffectResponse<IDescBloodPressureResponseDto>>>


    suspend fun getDiastolicMeasurements()
            : Flow<Status<EffectResponse<IDescBloodPressureResponseDto>>>


    suspend fun logoutFromLocalDatabase()

}//end IBloodPressureRepository