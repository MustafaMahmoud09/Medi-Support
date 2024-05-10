package com.example.account.setting.domain.usecase.execution

import com.example.account.setting.domain.repository.declarations.IAccountSettingRepository
import com.example.account.setting.domain.usecase.declarations.IUpdateProfileInfoUseCase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

class UpdateProfileInfoUseCase(
    private val accountSettingRepository: IAccountSettingRepository
) : IUpdateProfileInfoUseCase {

    //function for make request on repository for update profile info
    override suspend fun invoke(
        firstName: String?,
        lastName: String?,
        password: String?,
        imageUri: Any?
    ): Flow<Status<EffectResponse<Any>>> {

        return accountSettingRepository.updateProfileData(
            firstName = firstName,
            lastName = lastName,
            password = password,
            imageUri = imageUri
        )

    }//end invoke


}//end UpdateProfileInfoUseCase