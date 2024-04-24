package com.example.blood.pressure.domain.repository.declarations

import com.example.blood.pressure.domain.dto.declarations.descMeasurement.IDescBloodPressureResponseDto
import com.example.blood.pressure.domain.entity.declarations.IBloodPressureEntity
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IBloodPressureRepository {

    suspend fun createNewBloodPressureRecord(
        systolic: Int,
        diastolic: Int
    ): Flow<Status<EffectResponse<Any>>>


    suspend fun getLatestBloodPressureRecord()
            : Flow<List<IBloodPressureEntity>>


    suspend fun getLatestSystolicMeasurement()
            : Flow<Status<EffectResponse<IDescBloodPressureResponseDto>>>


    suspend fun getLatestDiastolicMeasurement()
            : Flow<Status<EffectResponse<IDescBloodPressureResponseDto>>>

}//end IBloodPressureRepository