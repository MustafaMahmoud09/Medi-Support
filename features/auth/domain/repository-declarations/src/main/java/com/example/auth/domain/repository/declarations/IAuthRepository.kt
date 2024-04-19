package com.example.auth.domain.repository.declarations

import com.example.auth.domain.dto.declarations.emailUser.IEmailUserDto
import com.example.auth.domain.dto.declarations.socialUser.ISocialUserDto
import com.example.libraries.core.local.data.entity.declarations.IUserEntity
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {

    suspend fun register(
        name: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Flow<Status<EffectResponse<Any>>>


    suspend fun sendEmail(
        email: String
    ): Flow<Status<EffectResponse<Any>>>


    suspend fun verifyCode(
        email: String,
        code: String
    ): Flow<Status<EffectResponse<Any>>>


    suspend fun resetPassword(
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<Status<EffectResponse<Any>>>


    suspend fun loginWithEmail(
        email: String,
        password: String
    ): Flow<Status<EffectResponse<IEmailUserDto>>>


    suspend fun loginWithSocial(
        accessToken: String,
        provider: String
    ): Flow<Status<EffectResponse<ISocialUserDto>>>


    suspend fun cachingUserData(
        firstName: String,
        lastName: String,
        email: String,
        token: String,
        path: String,
        remember: Boolean,
    )

    suspend fun getAuthUser(
        rememberMe: Boolean
    ): List<IUserEntity>

    suspend fun updateUsersAuthCount()

}//end IAuthRepository