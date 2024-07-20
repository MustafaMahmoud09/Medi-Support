package com.example.chat.domain.usecase.declarations

import com.example.chat.domain.model.DoctorModel
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IGetDoctorUseCase {

    suspend operator fun invoke(
        doctorId: Int
    ): Flow<Status<EffectResponse<DoctorModel>>>

}//end IGetChatsUseCase