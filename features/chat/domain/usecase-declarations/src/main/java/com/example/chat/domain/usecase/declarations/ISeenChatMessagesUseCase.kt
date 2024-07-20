package com.example.chat.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface ISeenChatMessagesUseCase {

    suspend operator fun invoke(
        doctorId: Int
    ): Flow<Status<EffectResponse<Any>>>

}//end IGetProfileInfoUseCase