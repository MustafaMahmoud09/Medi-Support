package com.example.room.domain.repository.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.room.domain.dto.declarations.roomToken.IRoomTokenResponseDto
import kotlinx.coroutines.flow.Flow

interface IOnlineRoomRepository {

    suspend fun getOnlineRoomToken(
       bookingId: Long
    ): Flow<Status<EffectResponse<IRoomTokenResponseDto>>>


    suspend fun startOnlineRoom(
        callId: Long
    ): Flow<Status<EffectResponse<Any>>>


    suspend fun finishOnlineRoom(
        callId: Long,
        bookingId: Long
    ): Flow<Status<EffectResponse<Any>>>

}//end IOnlineRoomRepository