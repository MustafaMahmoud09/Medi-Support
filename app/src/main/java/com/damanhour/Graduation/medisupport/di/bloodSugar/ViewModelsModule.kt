package com.damanhour.Graduation.medisupport.di.bloodSugar

import com.example.blood.sugar.domain.usecase.declarations.IAddNewBloodSugarRecordUseCase
import com.example.blood.sugar.domain.usecase.declarations.IGetBloodSugarStatusUseCase
import com.example.blood.sugar.domain.usecase.declarations.IGetLastHistoryRecordsUseCase
import com.example.blood.sugar.domain.usecase.declarations.IGetLastWeekBloodSugarRecordsUseCase
import com.example.blood.sugar.domain.usecase.declarations.IGetLatestBloodSugarMeasurementUseCase
import com.example.blood.sugar.domain.usecase.declarations.IGetPageHistoryRecordsUseCase
import com.example.blood.sugar.domain.usecase.declarations.ILogoutFromLocalDatabaseUseCase
import com.example.bloodsugar.presentation.uiState.viewModel.BloodSugarActivityViewModel
import com.example.bloodsugar.presentation.uiState.viewModel.BloodSugarHistoryViewModel
import com.example.bloodsugar.presentation.uiState.viewModel.RecordBloodSugarViewModel
import com.example.bloodsugar.presentation.uiState.viewModel.StatisticsBloodSugarViewModel
import com.example.libraries.shered.logic.usecase.declarations.IGetMonthDaysUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelsModule {

    @Provides
    fun provideRecordBloodSugarViewModel(
        addNewBloodSugarRecordUseCase: IAddNewBloodSugarRecordUseCase,
        getBloodSugarStatusUseCase: IGetBloodSugarStatusUseCase,
        getMonthDaysUseCase: IGetMonthDaysUseCase,
        logoutFromLocalDatabaseUseCase: ILogoutFromLocalDatabaseUseCase
    ): RecordBloodSugarViewModel {

        return RecordBloodSugarViewModel(
            addNewBloodSugarRecordUseCase = addNewBloodSugarRecordUseCase,
            getBloodSugarStatusUseCase = getBloodSugarStatusUseCase,
            getMonthDaysUseCase = getMonthDaysUseCase,
            logoutFromLocalDatabaseUseCase = logoutFromLocalDatabaseUseCase
        )

    }//end provideRecordBloodSugarViewModel


    @Provides
    fun provideStatisticsBloodSugarViewModel(
        getMonthDaysUseCase: IGetMonthDaysUseCase,
        getLatestBloodSugarMeasurementUseCase: IGetLatestBloodSugarMeasurementUseCase,
        getLastWeekBloodSugarRecordsUseCase: IGetLastWeekBloodSugarRecordsUseCase
    ): StatisticsBloodSugarViewModel {

        return StatisticsBloodSugarViewModel(
            getLatestBloodSugarMeasurementUseCase = getLatestBloodSugarMeasurementUseCase,
            getLastWeekBloodSugarRecordsUseCase = getLastWeekBloodSugarRecordsUseCase,
            getMonthDaysUseCase = getMonthDaysUseCase
        )

    }//end provideStatisticsBloodSugarViewModel


    @Provides
    fun provideBloodSugarActivityViewModel(
        getMonthDaysUseCase: IGetMonthDaysUseCase,
        getLatestBloodSugarMeasurementUseCase: IGetLatestBloodSugarMeasurementUseCase,
        getLastWeekBloodSugarRecordsUseCase: IGetLastWeekBloodSugarRecordsUseCase,
        getLastHistoryRecordsUseCase: IGetLastHistoryRecordsUseCase
    ): BloodSugarActivityViewModel {

        return BloodSugarActivityViewModel(
            getLatestBloodSugarMeasurementUseCase = getLatestBloodSugarMeasurementUseCase,
            getLastWeekBloodSugarRecordsUseCase = getLastWeekBloodSugarRecordsUseCase,
            getMonthDaysUseCase = getMonthDaysUseCase,
            getLastHistoryRecordsUseCase = getLastHistoryRecordsUseCase
        )

    }//end provideBloodSugarActivityViewModel


    @Provides
    fun provideBloodSugarHistoryViewModel(
        getPageHistoryRecordsUseCase: IGetPageHistoryRecordsUseCase
    ): BloodSugarHistoryViewModel {

        return BloodSugarHistoryViewModel(
            getPageHistoryRecordsUseCase = getPageHistoryRecordsUseCase
        )

    }//end provideBloodSugarHistoryViewModel

}//end ViewModelsModule