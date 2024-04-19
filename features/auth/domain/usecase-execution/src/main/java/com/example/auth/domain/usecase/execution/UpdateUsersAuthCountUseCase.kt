package com.example.auth.domain.usecase.execution

import com.example.auth.domain.repository.declarations.IAuthRepository
import com.example.auth.domain.usecase.declarations.IUpdateUsersAuthCountUseCase

class UpdateUsersAuthCountUseCase(
    private val authRepository: IAuthRepository
) : IUpdateUsersAuthCountUseCase {

    //function for update users auth count
    override suspend fun invoke() {
        authRepository.updateUsersAuthCount()
    }//end invoke

}//end UpdateUsersAuthCountUseCase