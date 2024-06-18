package com.damanhour.Graduation.medisupport.di.heartPrediction

import com.example.heart.prediction.data.source.remote.data.requests.HeartPredictionRequest
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
    fun provideHeartPredictionRequest(
        @Named("retrofit_with_token") retrofit: Retrofit
    ): HeartPredictionRequest {

        return retrofit.create(HeartPredictionRequest::class.java)

    }//end provideHeartRateRequest


}//end RequestsModule