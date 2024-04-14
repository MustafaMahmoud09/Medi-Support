package com.example.auth.domain.usecase.declarations

import com.example.auth.domain.dto.declarations.emailUser.IEmailUserDto
import com.example.auth.domain.dto.declarations.socialUser.ISocialUserDto

interface ICachingUserDataUseCase {

    suspend fun cachingSocialUser(user: ISocialUserDto)

    suspend fun cachingEmailUser(user: IEmailUserDto, remember: Boolean)

}//end ICachingUserDataUseCase