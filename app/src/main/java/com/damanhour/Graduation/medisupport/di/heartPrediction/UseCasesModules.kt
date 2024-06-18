package com.damanhour.Graduation.medisupport.di.heartPrediction

import com.example.heart.prediction.domain.repository.declarations.IHeartPredictionRepository
import com.example.heart.prediction.domain.usecase.declarations.IPredictHeartDiseaseUseCase
import com.example.heart.prediction.domain.usecase.execution.PredictHeartDiseaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModules {

    @Provides
    @Singleton
    fun providePredictHeartDiseaseUseCase(
        heartPredictionRepository: IHeartPredictionRepository
    ): IPredictHeartDiseaseUseCase {

        return PredictHeartDiseaseUseCase(
            heartPredictionRepository = heartPredictionRepository
        )

    }//end provideCheckPPGTechnologySupportedUseCase

}//end UseCasesModules