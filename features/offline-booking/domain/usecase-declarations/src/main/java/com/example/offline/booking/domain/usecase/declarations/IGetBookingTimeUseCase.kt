package com.example.offline.booking.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.offline.booking.domain.model.TimeModel
import kotlinx.coroutines.flow.Flow

interface IGetBookingTimeUseCase {

    suspend operator fun invoke(
        dateId: Long
    ): Flow<Status<EffectResponse<List<TimeModel>>>>

}//end GetBookingTimeUseCase