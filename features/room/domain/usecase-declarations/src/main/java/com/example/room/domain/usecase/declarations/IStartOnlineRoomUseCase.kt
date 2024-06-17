package com.example.room.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IStartOnlineRoomUseCase {

    suspend operator fun invoke(
        callId: Long
    ): Flow<Status<EffectResponse<Any>>>

}//end IStartOnlineRoomUseCase