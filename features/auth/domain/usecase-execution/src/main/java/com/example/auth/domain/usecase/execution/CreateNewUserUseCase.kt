package com.example.auth.domain.usecase.execution

import com.example.auth.domain.repository.declarations.IAuthRepository
import com.example.auth.domain.usecase.declarations.ICreateNewUserUseCase
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
    ): Flow<Int> {

        return authRepository.register(
            name = name,
            lastName = lastName,
            email = email,
            password = password,
            confirmPassword = confirmPassword
        )

    }//end invoke

}//end CreateNewUserUseCase