package com.damanhour.Graduation.medisupport.di.auth

import com.example.auth.domain.repository.declarations.IAuthRepository
import com.example.auth.domain.usecase.declarations.ICachingUserDataUseCase
import com.example.auth.domain.usecase.declarations.ICheckUserAuthUseCase
import com.example.auth.domain.usecase.declarations.ICreateNewUserUseCase
import com.example.auth.domain.usecase.declarations.ILoginWithEmailUseCase
import com.example.auth.domain.usecase.declarations.ILoginWithSocialUseCase
import com.example.auth.domain.usecase.declarations.IResetPasswordUseCase
import com.example.auth.domain.usecase.declarations.ISendUserEmailUseCase
import com.example.auth.domain.usecase.declarations.IVerifyCodeUseCase
import com.example.auth.domain.usecase.execution.CachingUserDataUseCase
import com.example.auth.domain.usecase.execution.CheckUserAuthUseCase
import com.example.auth.domain.usecase.execution.CreateNewUserUseCase
import com.example.auth.domain.usecase.execution.LoginWithEmailUseCase
import com.example.auth.domain.usecase.execution.LoginWithSocialUseCase
import com.example.auth.domain.usecase.execution.ResetPasswordUseCase
import com.example.auth.domain.usecase.execution.SendUserEmailUseCase
import com.example.auth.domain.usecase.execution.VerifyCodeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideCreateNewUserUseCase(
        authRepository: IAuthRepository
    ): ICreateNewUserUseCase {

        return CreateNewUserUseCase(
            authRepository = authRepository
        )

    }//end provideCreateNewUserUseCase


    @Provides
    @Singleton
    fun provideResetPasswordUseCase(
        authRepository: IAuthRepository
    ): IResetPasswordUseCase {

        return ResetPasswordUseCase(
            authRepository = authRepository
        )

    }//end provideResetPasswordUseCase


    @Provides
    @Singleton
    fun provideSendEmailUseCase(
        authRepository: IAuthRepository
    ): ISendUserEmailUseCase {

        return SendUserEmailUseCase(
            authRepository = authRepository
        )

    }//end provideSendEmailUseCase


    @Provides
    @Singleton
    fun provideVerifyCodeUseCase(
        authRepository: IAuthRepository
    ): IVerifyCodeUseCase {

        return VerifyCodeUseCase(
            authRepository = authRepository
        )

    }//end provideVerifyCodeUseCase


    @Provides
    @Singleton
    fun provideLoginWithFacebookUseCase(
        authRepository: IAuthRepository,
        cachingUserDataUseCase: ICachingUserDataUseCase
    ): ILoginWithSocialUseCase {

        return LoginWithSocialUseCase(
            authRepository = authRepository,
            cachingUserDataUseCase = cachingUserDataUseCase
        )

    }//end provideLoginWithFacebookUseCase


    @Provides
    @Singleton
    fun provideLoginWithEmailUseCase(
        authRepository: IAuthRepository,
        cachingUserDataUseCase: ICachingUserDataUseCase
    ): ILoginWithEmailUseCase {

        return LoginWithEmailUseCase(
            authRepository = authRepository,
            cachingUserDataUseCase = cachingUserDataUseCase
        )

    }//end provideLoginWithEmailUseCase


    @Provides
    @Singleton
    fun provideCachingUserDataUseCase(
        authRepository: IAuthRepository,
    ): ICachingUserDataUseCase {

        return CachingUserDataUseCase(
            authRepository = authRepository
        )

    }//end provideCachingUserDataUseCase


    @Provides
    @Singleton
    fun provideCheckUserAuthUseCase(
        authRepository: IAuthRepository
    ): ICheckUserAuthUseCase{

        return CheckUserAuthUseCase(
            authRepository = authRepository
        )

    }//end provideCheckUserAuthUseCase

}//end UseCasesModule