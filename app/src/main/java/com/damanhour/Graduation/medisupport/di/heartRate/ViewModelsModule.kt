package com.damanhour.Graduation.medisupport.di.heartRate

import android.content.Context
import com.example.heart.rate.domain.usecase.declarations.ICheckPPGTechnologySupportedUseCase
import com.example.heartrate.presentation.uiState.viewModel.measurement.MeasurementHeartRateViewModel
import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IImageProcessingHelper
import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IReflectedLightSignalHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelsModule {

    @Provides
    fun provideMeasurementHeartRateViewModel(
        @ApplicationContext context: Context,
        checkPPGTechnologySupportedUseCase: ICheckPPGTechnologySupportedUseCase,
        imageProcessingHelper: IImageProcessingHelper,
        reflectedLightSignalHelper: IReflectedLightSignalHelper
    ): MeasurementHeartRateViewModel {

        return MeasurementHeartRateViewModel(
            context = context,
            checkPPGTechnologySupportedUseCase = checkPPGTechnologySupportedUseCase,
            imageProcessingHelper = imageProcessingHelper,
            reflectedLightSignalHelper = reflectedLightSignalHelper
        )

    }//end provideMeasurementHeartRateViewModel

}//end ViewModelsModule