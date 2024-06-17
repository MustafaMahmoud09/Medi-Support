package com.example.room.domain.usecase.execution

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.room.domain.repository.declarations.IOnlineRoomRepository
import com.example.room.domain.usecase.declarations.IFinishOnlineRoomUseCase
import kotlinx.coroutines.flow.Flow

class FinishOnlineRoomUseCase(
    private val onlineRoomRepository: IOnlineRoomRepository,
) : IFinishOnlineRoomUseCase {

    //function for make request on repository for finish online room
    override suspend fun invoke(
        callId: Long,
        bookingId: Long
    ): Flow<Status<EffectResponse<Any>>> {

        return onlineRoomRepository.finishOnlineRoom(
            callId = callId,
            bookingId = bookingId
        )

    }//end invoke

}//end FinishOnlineRoomUseCase