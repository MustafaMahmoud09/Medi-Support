package com.example.auth.domain.usecase.execution

import com.example.auth.domain.repository.declarations.IAuthRepository
import com.example.auth.domain.usecase.declarations.IVerifyCodeUseCase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

class VerifyCodeUseCase(
    private val authRepository: IAuthRepository
) : IVerifyCodeUseCase {

    //function for verify from user code
    override suspend fun invoke(
        email: String,
        code: String
    ): Flow<Status<EffectResponse<Any>>>  {

        return authRepository.verifyCode(
            email = email,
            code = code
        )

    }//end VerifyCodeUseCase

}//end VerifyCodeUseCase