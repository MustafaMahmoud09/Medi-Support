package com.example.account.setting.domain.usecase.execution

import com.example.account.setting.domain.repository.declarations.IAccountSettingRepository
import com.example.account.setting.domain.usecase.declarations.ILogoutUseCase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

class LogoutUseCase(
    private val accountSettingRepository: IAccountSettingRepository
) : ILogoutUseCase {

    //function make request on repository for make logout
    override suspend fun invoke(): Flow<Status<EffectResponse<Any>>> {

        return accountSettingRepository.logout()

    }//end invoke

}//end LogoutUseCase