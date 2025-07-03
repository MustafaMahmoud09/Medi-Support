package com.example.chat.domain.usecase.declarations

import com.example.chat.domain.model.MessageModel
import com.example.libraries.core.remote.data.response.status.UnEffectResponse

interface IGetPageMessageUseCase {

    suspend operator fun invoke(
        page: Int,
        perPage: Int,
        doctorId: Int
    ): UnEffectResponse<List<MessageModel>>

}//end IGetPageMessageUseCase