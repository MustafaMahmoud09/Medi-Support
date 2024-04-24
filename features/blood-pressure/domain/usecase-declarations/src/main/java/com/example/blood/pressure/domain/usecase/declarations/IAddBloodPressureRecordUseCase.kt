package com.example.blood.pressure.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IAddBloodPressureRecordUseCase {

    suspend operator fun invoke(
        systolic: Int,
        diastolic: Int
    ): Flow<Status<EffectResponse<Any>>>

}//end IAddBloodPressureRecordUseCase