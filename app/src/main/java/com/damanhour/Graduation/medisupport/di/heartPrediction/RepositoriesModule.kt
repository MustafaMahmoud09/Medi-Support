package com.damanhour.Graduation.medisupport.di.heartPrediction

import com.example.heart.prediction.data.repository.execution.HeartPredictionRepositoryImpl
import com.example.heart.prediction.data.source.remote.data.requests.HeartPredictionRequest
import com.example.heart.prediction.domain.repository.declarations.IHeartPredictionRepository
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
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
    fun provideHeartPredictionRepository(
        wrapper: ResponseWrapper,
        heartPredictionRequest: HeartPredictionRequest
    ): IHeartPredictionRepository {

        return HeartPredictionRepositoryImpl(
            wrapper = wrapper,
            heartPredictionRequest = heartPredictionRequest
        )

    }//end provideHeartRateRepository

}//end RepositoriesModule