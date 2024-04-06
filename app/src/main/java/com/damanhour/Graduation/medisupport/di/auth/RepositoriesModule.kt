package com.damanhour.Graduation.medisupport.di.auth

import com.example.auth.data.repository.execution.AuthRepositoryImpl
import com.example.auth.data.source.remote.data.requests.AuthRequest
import com.example.auth.data.source.remote.data.requests.ResetPasswordRequest
import com.example.auth.domain.repository.declarations.IAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authRequest: AuthRequest,
        resetPasswordRequest: ResetPasswordRequest
    ): IAuthRepository {

        return AuthRepositoryImpl(
            authRequest = authRequest,
            resetPasswordRequest = resetPasswordRequest
        )

    }//end provideAuthRepository

}//end RepositoriesModule