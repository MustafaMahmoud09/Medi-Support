package com.example.auth.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.Response
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface ICreateNewUserUseCase {

    suspend operator fun invoke(
        name: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Flow<Status<Response<Any>>>

}//end ICreateNewUserUseCase