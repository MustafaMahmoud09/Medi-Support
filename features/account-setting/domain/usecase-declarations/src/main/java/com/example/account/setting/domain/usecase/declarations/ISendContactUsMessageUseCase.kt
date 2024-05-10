package com.example.account.setting.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface ISendContactUsMessageUseCase {

    suspend operator fun invoke(
        userName: String,
        email: String,
        message: String
    ): Flow<Status<EffectResponse<Any>>>

}//end ISendContactUsMessageUseCase