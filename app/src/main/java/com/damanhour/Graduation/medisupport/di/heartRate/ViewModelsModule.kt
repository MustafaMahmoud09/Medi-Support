package com.damanhour.Graduation.medisupport.di.heartRate

import android.content.Context
import com.example.heart.rate.domain.usecase.declarations.IAddNewHeartRateRecordUseCase
import com.example.heart.rate.domain.usecase.declarations.ICheckPPGTechnologySupportedUseCase
import com.example.heart.rate.domain.usecase.declarations.IGetLastHistoryRecordsUseCase
import com.example.heart.rate.domain.usecase.declarations.IGetLastWeekHeartRateRecordsUseCase
import com.example.heart.rate.domain.usecase.declarations.IGetLatestHeartRateMeasurementUseCase
import com.example.heart.rate.domain.usecase.declarations.IGetPageHistoryRecordsUseCase
import com.example.heart.rate.domain.usecase.declarations.ILogoutFromLocalDatabaseUseCase
import com.example.heartrate.presentation.uiState.viewModel.HeartRateActivityViewModel
import com.example.heartrate.presentation.uiState.viewModel.HeartRateHistoryViewModel
import com.example.heartrate.presentation.uiState.viewModel.StatisticsHeartRateViewModel
import com.example.heartrate.presentation.uiState.viewModel.measurement.MeasurementHeartRateViewModel
import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IDetectHeartBeatHelper
import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IImageProcessingHelper
import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IReflectedLightSignalHelper
import com.example.libraries.shered.logic.usecase.declarations.IGetMonthDaysUseCase
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
        reflectedLightSignalHelper: IReflectedLightSignalHelper,
        detectHeartBeatHelper: IDetectHeartBeatHelper,
        addNewHeartRateRecordUseCase: IAddNewHeartRateRecordUseCase,
        logoutFromLocalDatabaseUseCase: ILogoutFromLocalDatabaseUseCase
    ): MeasurementHeartRateViewModel {

        return MeasurementHeartRateViewModel(
            context = context,
            checkPPGTechnologySupportedUseCase = checkPPGTechnologySupportedUseCase,
            imageProcessingHelper = imageProcessingHelper,
            reflectedLightSignalHelper = reflectedLightSignalHelper,
            detectHeartBeatHelper = detectHeartBeatHelper,
            addNewHeartRateRecordUseCase = addNewHeartRateRecordUseCase,
            logoutFromLocalDatabaseUseCase = logoutFromLocalDatabaseUseCase
        )

    }//end provideMeasurementHeartRateViewModel


    @Provides
    fun provideStatisticsHeartRateViewModel(
        getMonthDaysUseCase: IGetMonthDaysUseCase,
        getLatestHeartRateMeasurementUseCase: IGetLatestHeartRateMeasurementUseCase,
        getLastWeekHeartRateRecordsUseCase: IGetLastWeekHeartRateRecordsUseCase
    ): StatisticsHeartRateViewModel {

        return StatisticsHeartRateViewModel(
            getMonthDaysUseCase = getMonthDaysUseCase,
            getLatestHeartRateMeasurementUseCase = getLatestHeartRateMeasurementUseCase,
            getLastWeekHeartRateRecordsUseCase = getLastWeekHeartRateRecordsUseCase
        )

    }//end provideStatisticsHeartRateViewModel


    @Provides
    fun provideHeartRateActivityViewModel(
        getMonthDaysUseCase: IGetMonthDaysUseCase,
        getLatestHeartRateMeasurementUseCase: IGetLatestHeartRateMeasurementUseCase,
        getLastWeekHeartRateRecordsUseCase: IGetLastWeekHeartRateRecordsUseCase,
        getLastHistoryRecordsUseCase: IGetLastHistoryRecordsUseCase
    ): HeartRateActivityViewModel {

        return HeartRateActivityViewModel(
            getMonthDaysUseCase = getMonthDaysUseCase,
            getLatestHeartRateMeasurementUseCase = getLatestHeartRateMeasurementUseCase,
            getLastWeekHeartRateRecordsUseCase = getLastWeekHeartRateRecordsUseCase,
            getLastHistoryRecordsUseCase = getLastHistoryRecordsUseCase

        )

    }//end provideHeartRateActivityViewModel


    @Provides
    fun provideHeartRateHistoryViewModel(
        getPageHistoryRecordsUseCase: IGetPageHistoryRecordsUseCase
    ): HeartRateHistoryViewModel {

        return HeartRateHistoryViewModel(
            getPageHistoryRecordsUseCase = getPageHistoryRecordsUseCase
        )

    }//end provideHeartRateHistoryViewModel

}//end ViewModelsModule