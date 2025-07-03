package com.damanhour.Graduation.medisupport.di.heartPrediction

import com.example.database_creator.MediSupportDatabase
import com.example.heart.prediction.data.repository.execution.HeartPredictionRepositoryImpl
import com.example.heart.prediction.data.source.remote.data.requests.HeartPredictionRequest
import com.example.heart.prediction.domain.repository.declarations.IHeartPredictionRepository
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
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
        heartPredictionRequest: HeartPredictionRequest,
        localDatabase: MediSupportDatabase,
        sharedPreferencesAccessObject: SharedPreferencesAccessObject
    ): IHeartPredictionRepository {

        return HeartPredictionRepositoryImpl(
            wrapper = wrapper,
            heartPredictionRequest = heartPredictionRequest,
            localDatabase = localDatabase,
            sharedPreferencesAccessObject = sharedPreferencesAccessObject
        )

    }//end provideHeartRateRepository

}//end RepositoriesModule