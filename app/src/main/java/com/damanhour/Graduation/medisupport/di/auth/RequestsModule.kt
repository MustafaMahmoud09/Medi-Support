package com.damanhour.Graduation.medisupport.di.auth

import com.example.auth.data.source.remote.data.requests.AuthRequest
import com.example.auth.data.source.remote.data.requests.ResetPasswordRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RequestsModule {

    @Provides
    @Singleton
    fun provideAuthRequest(
        @Named("retrofit_without_token") retrofit: Retrofit
    ): AuthRequest {

        return retrofit.create(AuthRequest::class.java)

    }//end provideAuthRequest


    @Provides
    @Singleton
    fun provideResetPasswordRequest(
        @Named("retrofit_without_token") retrofit: Retrofit
    ): ResetPasswordRequest {

        return retrofit.create(ResetPasswordRequest::class.java)

    }//end provideAuthRequest

}//end RequestsModule