package com.example.auth.domain.usecase.execution

import com.example.auth.domain.repository.declarations.IAuthRepository
import com.example.auth.domain.usecase.declarations.ICreateNewUserUseCase
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

class CreateNewUserUseCase(
    private val authRepository: IAuthRepository
) : ICreateNewUserUseCase {

    override suspend fun invoke(
        name: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Flow<Status<Int>> {

        return authRepository.register(
            name = name,
            lastName = lastName,
            email = email,
            password = password,
            confirmPassword = confirmPassword
        )

    }//end invoke

}//end CreateNewUserUseCase