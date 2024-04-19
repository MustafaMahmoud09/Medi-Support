package com.example.auth.domain.usecase.execution

import com.example.auth.domain.repository.declarations.IAuthRepository
import com.example.auth.domain.usecase.declarations.IResetPasswordUseCase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

class ResetPasswordUseCase(
    private val authRepository: IAuthRepository
) : IResetPasswordUseCase {

    //function for reset password
    override suspend fun invoke(
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<Status<EffectResponse<Any>>>  {

        return authRepository.resetPassword(
            email = email,
            password = password,
            passwordConfirmation = passwordConfirmation
        )

    }//end invoke

}//end ResetPasswordUseCase