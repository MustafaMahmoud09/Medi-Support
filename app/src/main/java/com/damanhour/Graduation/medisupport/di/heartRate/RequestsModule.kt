package com.damanhour.Graduation.medisupport.di.heartRate

import com.example.heart.rate.data.source.remote.data.requests.HeartRateRequest
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
    fun provideHeartRateRequest(
        @Named("retrofit_with_token") retrofit: Retrofit
    ): HeartRateRequest {

        return retrofit.create(HeartRateRequest::class.java)

    }//end provideHeartRateRequest


}//end RequestsModule