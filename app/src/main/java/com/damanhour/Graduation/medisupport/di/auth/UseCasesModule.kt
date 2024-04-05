package com.damanhour.Graduation.medisupport.di.auth

import com.example.auth.domain.repository.declarations.IAuthRepository
import com.example.auth.domain.usecase.declarations.ICreateNewUserUseCase
import com.example.auth.domain.usecase.execution.CreateNewUserUseCase
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
    ) : ICreateNewUserUseCase{

        return CreateNewUserUseCase(
            authRepository = authRepository
        )

    }//end provideCreateNewUserUseCase

}//end UseCasesModule