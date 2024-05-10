package com.example.account.setting.domain.usecase.execution

import com.example.account.setting.domain.repository.declarations.IAccountSettingRepository
import com.example.account.setting.domain.usecase.declarations.IDeleteAccountUseCase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

class DeleteAccountUseCase(
    private val accountSettingRepository: IAccountSettingRepository
) : IDeleteAccountUseCase {

    //function for make request on repository for delete account
    override suspend fun invoke(): Flow<Status<EffectResponse<Any>>> {

        return accountSettingRepository.deleteUserAccount()

    }//end invoke

}//end DeleteAccountUseCase