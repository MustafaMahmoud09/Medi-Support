package com.damanhour.Graduation.medisupport.di.auth

import com.example.auth.domain.repository.declarations.IAuthRepository
import com.example.auth.domain.usecase.declarations.ICreateNewUserUseCase
import com.example.auth.domain.usecase.declarations.IResetPasswordUseCase
import com.example.auth.domain.usecase.declarations.ISendUserEmailUseCase
import com.example.auth.domain.usecase.declarations.IVerifyCodeUseCase
import com.example.auth.domain.usecase.execution.CreateNewUserUseCase
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

}//end UseCasesModule