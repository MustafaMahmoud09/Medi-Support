package com.example.auth.domain.usecase.execution

import com.example.auth.domain.dto.declarations.emailUser.IEmailUserDto
import com.example.auth.domain.dto.declarations.socialUser.ISocialUserDto
import com.example.auth.domain.repository.declarations.IAuthRepository
import com.example.auth.domain.usecase.declarations.ICachingUserDataUseCase

class CachingUserDataUseCase(
    private val authRepository: IAuthRepository
) : ICachingUserDataUseCase {

    //function for store social user in local database
    override suspend fun cachingSocialUser(user: ISocialUserDto) {

        //execute caching user data here
        authRepository.cachingUserData(
            firstName = user.data?.user?.firstName ?: "",
            lastName = user.data?.user?.lastName ?: "",
            email = user.data?.user?.email ?: "",
            token = user.data?.token ?: "",
            path = "",
            remember = true
        )

    }//end cachingSocialUser


    //function for store email user in local database
    override suspend fun cachingEmailUser(user: IEmailUserDto, remember: Boolean) {

        //execute caching user data here
        authRepository.cachingUserData(
            firstName = user.user?.firstName ?: "",
            lastName = user.user?.lastName ?: "",
            email = user.user?.email ?: "",
            token = user.accessToken ?: "",
            path = user.user?.avatar ?: "",
            remember = remember
        )

    }//end cachingEmailUser

}//end CachingUserDataUseCase