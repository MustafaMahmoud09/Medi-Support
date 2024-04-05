package com.example.auth.domain.usecase.declarations

import kotlinx.coroutines.flow.Flow

interface ICreateNewUserUseCase {

    suspend operator fun invoke(
        name: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Flow<Int>

}//end ICreateNewUserUseCase