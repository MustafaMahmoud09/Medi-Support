package com.example.account.setting.domain.usecase.execution

import com.example.account.setting.domain.repository.declarations.IAccountSettingRepository
import com.example.account.setting.domain.usecase.declarations.ISendContactUsMessageUseCase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

class SendContactUsMessageUseCase(
    private val accountSettingRepository: IAccountSettingRepository
) : ISendContactUsMessageUseCase {

    //function for make request on repository for send contact us message
    override suspend fun invoke(
        userName: String,
        email: String,
        message: String
    ): Flow<Status<EffectResponse<Any>>> {

        return accountSettingRepository.sendContactUsMessage(
            userName = userName,
            email = email,
            message = message
        )

    }//end invoke

}//end SendContactUsMessageUseCase