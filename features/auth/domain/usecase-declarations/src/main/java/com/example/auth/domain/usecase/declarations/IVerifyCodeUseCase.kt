package com.example.auth.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IVerifyCodeUseCase {

    suspend operator fun invoke(
        email: String,
        code: String
    ): Flow<Status<Int>>

}//end IVerifyCodeUseCase