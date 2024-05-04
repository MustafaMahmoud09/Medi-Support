package com.example.online.booking.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IBookOnlineAppointmentUseCase {

    suspend operator fun invoke(
        doctorId: Long
    ): Flow<Status<EffectResponse<Any>>>

}//end IBookOnlineAppointmentUseCase