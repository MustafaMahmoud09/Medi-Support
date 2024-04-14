package com.example.auth.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface ILoginWithSocialUseCase {

    suspend operator fun invoke(
        accessToken: String,
        provider: String
    ): Flow<Status<Int>>

}//end ILoginWithFacebookUseCase