package com.example.auth.domain.usecase.execution

import com.example.auth.domain.repository.declarations.IAuthRepository
import com.example.auth.domain.usecase.declarations.ICheckUserAuthUseCase

class CheckUserAuthUseCase(
    private val authRepository: IAuthRepository
) : ICheckUserAuthUseCase {

    //function for check user have authentication before or no
    override suspend fun invoke(): Boolean {

        //get list contain on users is authenticated
        val users = authRepository.getAuthUser(
            rememberMe = true
        )

        //if users is empty return false else return true
        return users.isNotEmpty()

    }//end invoke

}//end CheckUserAuthUseCase