package com.example.auth.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IResetPasswordUseCase {

    suspend operator fun invoke(
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<Status<Int>>

}//end IResetPasswordUseCase