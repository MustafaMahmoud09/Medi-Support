package com.example.chat.domain.usecase.declarations

import com.example.chat.domain.model.ChatModel
import com.example.libraries.core.remote.data.response.status.UnEffectResponse

interface IGetPageChatsUseCase {

    suspend operator fun invoke(
        page: Int,
        perPage: Int
    ): UnEffectResponse<List<ChatModel>>

}//end IGetChatsUseCase