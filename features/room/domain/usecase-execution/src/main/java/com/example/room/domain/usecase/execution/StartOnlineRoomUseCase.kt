package com.example.room.domain.usecase.execution

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.room.domain.repository.declarations.IOnlineRoomRepository
import com.example.room.domain.usecase.declarations.IStartOnlineRoomUseCase
import kotlinx.coroutines.flow.Flow

class StartOnlineRoomUseCase(
    private val onlineRoomRepository: IOnlineRoomRepository,
) : IStartOnlineRoomUseCase {

    //function for make request on repository for start room
    override suspend fun invoke(
        callId: Long
    ): Flow<Status<EffectResponse<Any>>> {

        return onlineRoomRepository.startOnlineRoom(
            callId = callId
        )

    }//end invoke

}//end StartOnlineRoomUseCase