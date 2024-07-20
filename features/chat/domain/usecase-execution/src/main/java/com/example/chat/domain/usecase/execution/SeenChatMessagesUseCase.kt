package com.example.chat.domain.usecase.execution

import com.example.chat.domain.repository.declarations.IChatRepository
import com.example.chat.domain.usecase.declarations.ISeenChatMessagesUseCase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

class SeenChatMessagesUseCase(
    private val chatRepository: IChatRepository,
): ISeenChatMessagesUseCase {

    override suspend fun invoke(
        doctorId: Int
    ): Flow<Status<EffectResponse<Any>>> {

        return chatRepository.seenChatMessages(
            doctorId = doctorId
        )

    }//end invoke

}//end SeenChatMessagesUseCase