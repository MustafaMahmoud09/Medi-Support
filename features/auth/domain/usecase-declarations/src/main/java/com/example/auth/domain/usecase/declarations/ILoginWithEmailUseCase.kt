package com.example.auth.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface ILoginWithEmailUseCase {

    suspend operator fun invoke(
        email: String,
        password: String,
        remember: Boolean
    ): Flow<Status<Int>>

}//end ILoginWithEmailUseCase