package com.example.account.setting.domain.repository.declarations

import com.example.libraries.core.local.data.entity.declarations.IUserEntity
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IAccountSettingRepository {

    suspend fun updateProfileData(
        firstName: String?,
        lastName: String?,
        password: String?,
        imageUri: Any?
    ): Flow<Status<EffectResponse<Any>>>


    suspend fun getAccountInfo(): Flow<List<IUserEntity>>


    suspend fun sendContactUsMessage(
        userName: String,
        email: String,
        message: String
    ): Flow<Status<EffectResponse<Any>>>


    suspend fun deleteUserAccount(): Flow<Status<EffectResponse<Any>>>


    suspend fun logout(): Flow<Status<EffectResponse<Any>>>


    suspend fun logoutFromLocalDatabase()

}//end IAccountSettingRepository