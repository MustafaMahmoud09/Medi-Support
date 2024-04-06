package com.example.auth.domain.repository.declarations

import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {

    suspend fun register(
        name: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Flow<Status<Int>>


    suspend fun sendEmail(
        email: String
    ): Flow<Status<Int>>


    suspend fun verifyCode(
        email: String,
        code: String
    ): Flow<Status<Int>>


    suspend fun resetPassword(
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<Status<Int>>


}//end IAuthRepository