package com.example.auth.domain.usecase.execution

import com.example.auth.domain.repository.declarations.IAuthRepository
import com.example.auth.domain.usecase.declarations.ISendUserEmailUseCase
import com.example.libraries.core.remote.data.response.status.Response
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

class SendUserEmailUseCase(
    private val authRepository: IAuthRepository
) : ISendUserEmailUseCase {

    //function for send user email
    override suspend fun invoke(
        email: String
    ): Flow<Status<Response<Any>>>  {

        return authRepository.sendEmail(
            email = email
        )

    }//end invoke

}//SendUserEmailUseCase