package com.example.auth.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface ISendUserEmailUseCase {

    suspend operator fun invoke(
        email: String
    ): Flow<Status<EffectResponse<Any>>>

}//end ISendUserEmailUseCase