package com.damanhour.Graduation.medisupport.di.bloodPressure

import com.example.blood.pressure.domain.usecase.declarations.IAddBloodPressureRecordUseCase
import com.example.blood.pressure.domain.usecase.declarations.IGetLastHistoryRecordsUseCase
import com.example.blood.pressure.domain.usecase.declarations.IGetLastWeekDiastolicRecordsUseCase
import com.example.blood.pressure.domain.usecase.declarations.IGetLastWeekSystolicRecordsUseCase
import com.example.blood.pressure.domain.usecase.declarations.IGetLatestBloodPressureMeasurementUserCase
import com.example.blood.pressure.domain.usecase.declarations.IGetPageHistoryRecordUseCase
import com.example.blood.pressure.domain.usecase.declarations.ILogoutFromLocalDatabaseUseCase
import com.example.bloodpressure.presentation.uiState.viewModel.BloodPressureActivityViewModel
import com.example.bloodpressure.presentation.uiState.viewModel.BloodPressureHistoryViewModel
import com.example.bloodpressure.presentation.uiState.viewModel.RecordBloodPressureViewModel
import com.example.bloodpressure.presentation.uiState.viewModel.StatisticsBloodPressureViewModel
import com.example.libraries.shered.logic.usecase.declarations.IGetMonthDaysUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelsModule {

    @Provides
    fun provideRecordBloodPressureViewModel(
        addBloodPressureRecordUseCase: IAddBloodPressureRecordUseCase,
        getLatestBloodPressureMeasurementUserCase: IGetLatestBloodPressureMeasurementUserCase,
        getMonthDaysUseCase: IGetMonthDaysUseCase,
        logoutFromLocalDatabaseUseCase: ILogoutFromLocalDatabaseUseCase
    ): RecordBloodPressureViewModel {

        return RecordBloodPressureViewModel(
            addBloodPressureRecordUseCase = addBloodPressureRecordUseCase,
            getMonthDaysUseCase = getMonthDaysUseCase,
            getLatestBloodPressureMeasurementUserCase = getLatestBloodPressureMeasurementUserCase,
            logoutFromLocalDatabaseUseCase = logoutFromLocalDatabaseUseCase
        )

    }//end provideRecordBloodPressureViewModel


    @Provides
    fun provideStatisticsBloodPressureViewModel(
        getLatestBloodPressureMeasurementUserCase: IGetLatestBloodPressureMeasurementUserCase,
        getMonthDaysUseCase: IGetMonthDaysUseCase,
        getLastWeekSystolicRecordsUseCase: IGetLastWeekSystolicRecordsUseCase,
        getLastWeekDiastolicRecordsUseCase: IGetLastWeekDiastolicRecordsUseCase,
        logoutFromLocalDatabaseUseCase: ILogoutFromLocalDatabaseUseCase
    ): StatisticsBloodPressureViewModel {

        return StatisticsBloodPressureViewModel(
            getMonthDaysUseCase = getMonthDaysUseCase,
            getLatestBloodPressureMeasurementUserCase = getLatestBloodPressureMeasurementUserCase,
            getLastWeekSystolicRecordsUseCase = getLastWeekSystolicRecordsUseCase,
            getLastWeekDiastolicRecordsUseCase = getLastWeekDiastolicRecordsUseCase,
            logoutFromLocalDatabaseUseCase = logoutFromLocalDatabaseUseCase
        )

    }//end provideStatisticsBloodPressureViewModel


    @Provides
    fun provideBloodPressureActivityViewModel(
        getLatestBloodPressureMeasurementUserCase: IGetLatestBloodPressureMeasurementUserCase,
        getMonthDaysUseCase: IGetMonthDaysUseCase,
        getLastWeekSystolicRecordsUseCase: IGetLastWeekSystolicRecordsUseCase,
        getLastWeekDiastolicRecordsUseCase: IGetLastWeekDiastolicRecordsUseCase,
        getLastHistoryRecordsUseCase: IGetLastHistoryRecordsUseCase,
        logoutFromLocalDatabaseUseCase: ILogoutFromLocalDatabaseUseCase
    ): BloodPressureActivityViewModel {

        return BloodPressureActivityViewModel(
            getMonthDaysUseCase = getMonthDaysUseCase,
            getLatestBloodPressureMeasurementUserCase = getLatestBloodPressureMeasurementUserCase,
            getLastWeekSystolicRecordsUseCase = getLastWeekSystolicRecordsUseCase,
            getLastWeekDiastolicRecordsUseCase = getLastWeekDiastolicRecordsUseCase,
            getLastHistoryRecordsUseCase = getLastHistoryRecordsUseCase,
            logoutFromLocalDatabaseUseCase = logoutFromLocalDatabaseUseCase
        )

    }//end provideStatisticsBloodPressureViewModel


    @Provides
    fun provideBloodPressureHistoryViewModel(
        getPageHistoryRecordUseCase: IGetPageHistoryRecordUseCase
    ): BloodPressureHistoryViewModel {

        return BloodPressureHistoryViewModel(
            getPageHistoryRecordUseCase = getPageHistoryRecordUseCase
        )

    }//end provideBloodPressureHistoryViewModel

}//end ViewModelsModule