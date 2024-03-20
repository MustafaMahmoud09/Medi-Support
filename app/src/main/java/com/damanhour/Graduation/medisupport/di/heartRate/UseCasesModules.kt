package com.damanhour.Graduation.medisupport.di.heartRate

import com.example.heart.rate.domain.repository.declarations.IHeartRateRepository
import com.example.heart.rate.domain.usecase.declarations.ICheckPPGTechnologySupportedUseCase
import com.example.heartrate.domain.usecase.CheckPPGTechnologySupportedUseCase
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
    fun provideCheckPPGTechnologySupportedUseCase(
        heartRateRepository: IHeartRateRepository
    ): ICheckPPGTechnologySupportedUseCase {

        return CheckPPGTechnologySupportedUseCase(
            heartRateRepository = heartRateRepository
        )

    }//end provideCheckPPGTechnologySupportedUseCase

}//end UseCasesModules