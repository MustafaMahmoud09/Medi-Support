package com.example.offline.booking.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IRateOfflineDoctorUseCase {

    suspend operator fun invoke(
        doctorId: Long,
        rate: Int
    ): Flow<Status<EffectResponse<Any>>>

}//end IRateOfflineDoctorUseCase