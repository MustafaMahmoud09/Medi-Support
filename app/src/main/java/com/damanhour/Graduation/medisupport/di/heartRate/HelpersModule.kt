package com.damanhour.Graduation.medisupport.di.heartRate

import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IImageProcessingHelper
import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IReflectedLightSignalHelper
import com.example.heartrate.presentation.uiState.viewModel.measurement.helperExecution.ImageProcessingHelper
import com.example.heartrate.presentation.uiState.viewModel.measurement.helperExecution.ReflectedLightSignalHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HelpersModule {

    @Provides
    @Singleton
    fun provideImageProcessingHelper(): IImageProcessingHelper {

        return ImageProcessingHelper()

    }//end provideImageProcessingHelper


    @Provides
    @Singleton
    fun provideReflectedLightSignalHelper(): IReflectedLightSignalHelper {

        return ReflectedLightSignalHelper()

    }//end provideReflectedLightSignalHelper

}//end HelpersModule