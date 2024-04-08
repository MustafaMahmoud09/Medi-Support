package com.example.auth.domain.usecase.declarations

import kotlinx.coroutines.flow.Flow

interface LoginWithGoogleUseCase {

    suspend operator fun invoke(accessToken: String): Flow<Int>

}//end LoginWithGoogleUseCase