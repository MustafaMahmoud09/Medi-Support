package com.damanhour.Graduation.medisupport.di.auth

import com.example.auth.data.repository.execution.AuthRepositoryImpl
import com.example.auth.data.source.remote.data.requests.AuthRequest
import com.example.auth.data.source.remote.data.requests.ResetPasswordRequest
import com.example.auth.domain.repository.declarations.IAuthRepository
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authRequest: AuthRequest,
        resetPasswordRequest: ResetPasswordRequest,
        responseWrapper: ResponseWrapper,
        localDatabase: MediSupportDatabase,
        sharedPreferencesAccessObject: SharedPreferencesAccessObject
    ): IAuthRepository {

        return AuthRepositoryImpl(
            authRequest = authRequest,
            resetPasswordRequest = resetPasswordRequest,
            responseWrapper = responseWrapper,
            localDatabase = localDatabase,
            sharedPreferencesAccessObject = sharedPreferencesAccessObject
        )

    }//end provideAuthRepository

}//end RepositoriesModule