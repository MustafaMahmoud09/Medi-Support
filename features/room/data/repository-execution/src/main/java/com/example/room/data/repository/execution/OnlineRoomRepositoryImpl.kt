package com.example.room.data.repository.execution

import com.example.database_creator.MediSupportDatabase
import com.example.dto_execution.roomToken.RoomTokenResponseDto
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.room.data.source.remote.data.requests.OnlineRoomRequest
import com.example.room.domain.dto.declarations.roomToken.IRoomTokenResponseDto
import com.example.room.domain.repository.declarations.IOnlineRoomRepository
import kotlinx.coroutines.flow.Flow

class OnlineRoomRepositoryImpl(
    private val onlineRoomRequest: OnlineRoomRequest,
    private val wrapper: ResponseWrapper,
    private val localDatabase: MediSupportDatabase,
) : IOnlineRoomRepository {

    //function for make request on server for get room token
    override suspend fun getOnlineRoomToken(
        bookingId: Long
    ): Flow<Status<EffectResponse<IRoomTokenResponseDto>>> {

        return wrapper.wrapper<IRoomTokenResponseDto, RoomTokenResponseDto> {
            onlineRoomRequest.getOnlineRoomToken(
                bookingId = bookingId
            )
        }//end wrapper

    }//end getOnlineRoomToken


    //function for make request on server for start online room
    override suspend fun startOnlineRoom(
        callId: Long
    ): Flow<Status<EffectResponse<Any>>> {

        return wrapper.infiniteWrapper<Any, Any> {
            onlineRoomRequest.startOnlineRoom(
                callId = callId
            )
        }//end infiniteWrapper

    }//end startOnlineRoom


    //function for make request on server for finish online
    override suspend fun finishOnlineRoom(
        callId: Long,
        bookingId: Long
    ): Flow<Status<EffectResponse<Any>>> {

        localDatabase.onlineBookingDao().updateOnlineBookingStatusById(
            id = bookingId,
            status = 3
        )

        return wrapper.infiniteWrapper<Any, Any> {
            onlineRoomRequest.finishOnlineRoom(
                callId = callId,
                bookingId = bookingId
            )
        }//end infiniteWrapper

    }//end finishOnlineRoom

}//end OnlineRoomRepositoryImpl