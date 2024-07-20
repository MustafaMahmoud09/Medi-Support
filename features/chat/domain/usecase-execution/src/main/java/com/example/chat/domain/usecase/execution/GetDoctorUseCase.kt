package com.example.chat.domain.usecase.declarations

import com.example.chat.domain.model.ChatModel
import com.example.chat.domain.model.DoctorModel
import com.example.libraries.core.remote.data.response.status.UnEffectResponse

interface IGetDoctorUseCase {

    suspend operator fun invoke(
        doctorId: Int
    ): UnEffectResponse<List<DoctorModel>>

}//end IGetChatsUseCase