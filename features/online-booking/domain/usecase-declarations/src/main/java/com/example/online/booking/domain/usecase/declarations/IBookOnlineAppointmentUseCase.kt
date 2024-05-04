package com.example.offline.booking.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IBookOfflineAppointmentUseCase {

    suspend operator fun invoke(
        dateId: Long,
        timeId: Long,
        doctorId: Long
    ): Flow<Status<EffectResponse<Any>>>

}//end IBookOfflineAppointmentUseCase