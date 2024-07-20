package com.example.chat.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IGetChannelAuthTokenUseCase {

    suspend operator fun invoke(
        socketId: String,
        channelName: String
    ): Flow<Status<EffectResponse<String>>>

}//end IGetChatsUseCase