package com.example.chat.domain.usecase.declarations

import com.example.chat.domain.model.MessageModel
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface ISendChatMessageUseCase {

    suspend operator fun invoke(
        doctorId: Int,
        message: String?,
        temporaryMsgId: String,
        uri: Any?
    ): Flow<Status<EffectResponse<MessageModel>>>

}//end IGetProfileInfoUseCase