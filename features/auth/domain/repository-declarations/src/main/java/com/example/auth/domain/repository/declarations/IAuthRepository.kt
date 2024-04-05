package com.example.auth.domain.repository.declarations

import kotlinx.coroutines.flow.Flow

interface IAuthRepository {

    suspend fun register(
        name: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Flow<Int>

}//end IAuthRepository