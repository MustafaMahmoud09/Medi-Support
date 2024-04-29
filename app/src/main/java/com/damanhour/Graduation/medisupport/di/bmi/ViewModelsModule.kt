package com.damanhour.Graduation.medisupport.di.bmi

import com.example.bmi.domain.usecase.declarations.IAddNewBMIRecordUseCase
import com.example.bmi.domain.usecase.declarations.IGetLastHistoryRecordsUseCase
import com.example.bmi.domain.usecase.declarations.IGetLastWeekBMIRecordsUseCase
import com.example.bmi.domain.usecase.declarations.IGetLatestBMIMeasurementUseCase
import com.example.bmi.domain.usecase.declarations.IGetPageHistoryRecordsUseCase
import com.example.bmi.presentation.uiState.viewModel.BMIActivityViewModel
import com.example.bmi.presentation.uiState.viewModel.BMIHistoryViewModel
import com.example.bmi.presentation.uiState.viewModel.DeterminationBMIViewModel
import com.example.bmi.presentation.uiState.viewModel.RecordBMIViewModel
import com.example.libraries.shered.logic.usecase.declarations.IGetMonthDaysUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelsModule {

    @Provides
    fun provideRecordBMIViewModel(
        addNewBMIRecordUseCase: IAddNewBMIRecordUseCase
    ): RecordBMIViewModel {

        return RecordBMIViewModel(
            addNewBMIRecordUseCase = addNewBMIRecordUseCase
        )

    }//end provideRecordBMIViewModel


    @Provides
    fun provideDeterminationBMIViewModel(
        getLatestBMIMeasurementUseCase: IGetLatestBMIMeasurementUseCase
    ): DeterminationBMIViewModel {

        return DeterminationBMIViewModel(
            getLatestBMIMeasurementUseCase = getLatestBMIMeasurementUseCase
        )

    }//end provideDeterminationBMIViewModel


    @Provides
    fun provideBMIActivityViewModel(
        getMonthDaysUseCase: IGetMonthDaysUseCase,
        getLatestBMIMeasurementUseCase: IGetLatestBMIMeasurementUseCase,
        getLastWeekBMIRecordsUseCase: IGetLastWeekBMIRecordsUseCase,
        getLastHistoryRecordsUseCase: IGetLastHistoryRecordsUseCase
    ): BMIActivityViewModel {

        return BMIActivityViewModel(
            getMonthDaysUseCase = getMonthDaysUseCase,
            getLatestBMIMeasurementUseCase = getLatestBMIMeasurementUseCase,
            getLastWeekBMIRecordsUseCase = getLastWeekBMIRecordsUseCase,
            getLastHistoryRecordsUseCase = getLastHistoryRecordsUseCase
        )

    }//end provideBMIActivityViewModel


    @Provides
    fun provideBMIHistoryViewModel(
        getPageHistoryRecordsUseCase: IGetPageHistoryRecordsUseCase
    ): BMIHistoryViewModel {

        return BMIHistoryViewModel(
            getPageHistoryRecordsUseCase = getPageHistoryRecordsUseCase
        )

    }//end provideBMIHistoryViewModel

}//end ViewModelsModule