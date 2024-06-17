package com.example.room.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IFinishOnlineRoomUseCase {

    suspend operator fun invoke(
        callId: Long,
        bookingId: Long
    ): Flow<Status<EffectResponse<Any>>>

}//end IFinishOnlineRoomUseCase