package com.example.room.domain.usecase.declarations

import com.example.domain_model.OnlineRoomModel
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IGetOnlineRoomUseCase {

    suspend operator fun invoke(
        bookingId: Long
    ) : Flow<Status<EffectResponse<List<OnlineRoomModel>>>>

}//end IGetOnlineRoomUseCase