package com.damanhour.Graduation.medisupport.di.heartRate

import android.content.Context
import com.example.heart.rate.domain.usecase.declarations.ICheckPPGTechnologySupportedUseCase
import com.example.heartrate.presentation.uiState.viewModel.MeasurementHeartRateViewModel
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
        checkPPGTechnologySupportedUseCase: ICheckPPGTechnologySupportedUseCase
    ): MeasurementHeartRateViewModel {

        return MeasurementHeartRateViewModel(
            context = context,
            checkPPGTechnologySupportedUseCase = checkPPGTechnologySupportedUseCase
        )

    }//end provideMeasurementHeartRateViewModel

}//end ViewModelsModule