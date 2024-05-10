package com.example.account.setting.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IUpdateProfileInfoUseCase {

    suspend operator fun invoke(
        firstName: String?,
        lastName: String?,
        password: String?,
        imageUri: Any?
    ): Flow<Status<EffectResponse<Any>>>

}//end IUpdateProfileInfoUseCase