package com.example.account.setting.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IDeleteAccountUseCase {

    suspend operator fun invoke(): Flow<Status<EffectResponse<Any>>>

}//end IDeleteAccountUseCase