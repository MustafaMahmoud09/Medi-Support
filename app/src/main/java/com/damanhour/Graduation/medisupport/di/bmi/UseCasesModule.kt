package com.damanhour.Graduation.medisupport.di.bmi

import com.example.blood.sugar.domain.mapper.declarations.child.IBMIEntityToAdviceBMIModelMapper
import com.example.blood.sugar.domain.mapper.declarations.child.IBMIEntityToChartBMIModelMapper
import com.example.blood.sugar.domain.mapper.declarations.child.IBMIEntityToSimpleBMIModelMapper
import com.example.bmi.domain.repository.declarations.IBMIRepository
import com.example.bmi.domain.usecase.declarations.IAddNewBMIRecordUseCase
import com.example.bmi.domain.usecase.declarations.IGetLastHistoryRecordsUseCase
import com.example.bmi.domain.usecase.declarations.IGetLastWeekBMIRecordsUseCase
import com.example.bmi.domain.usecase.declarations.IGetLatestBMIMeasurementUseCase
import com.example.bmi.domain.usecase.declarations.IGetPageHistoryRecordsUseCase
import com.example.bmi.domain.usecase.declarations.ILogoutFromLocalDatabaseUseCase
import com.example.bmi.domain.usecase.execution.AddNewBMIRecordUseCase
import com.example.bmi.domain.usecase.execution.GetLastHistoryRecordsUseCase
import com.example.bmi.domain.usecase.execution.GetLastWeekBMIRecordsUseCase
import com.example.bmi.domain.usecase.execution.GetLatestBMIMeasurementUseCase
import com.example.bmi.domain.usecase.execution.GetPageHistoryRecordsUseCase
import com.example.bmi.domain.usecase.execution.LogoutFromLocalDatabaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {


    @Provides
    @Singleton
    fun provideAddNewBMIRecordUseCase(
        bmiRepository: IBMIRepository
    ): IAddNewBMIRecordUseCase {

        return AddNewBMIRecordUseCase(
            bmiRepository = bmiRepository,
        )

    }//end provideAddNewBMIRecordUseCase


    @Provides
    @Singleton
    fun provideGetLatestBMIMeasurementUseCase(
        bmiRepository: IBMIRepository,
        bmiEntityToAdviceBMIModelMapper: IBMIEntityToAdviceBMIModelMapper
    ): IGetLatestBMIMeasurementUseCase {

        return GetLatestBMIMeasurementUseCase(
            bmiRepository = bmiRepository,
            bmiEntityToAdviceBMIModelMapper = bmiEntityToAdviceBMIModelMapper
        )

    }


    @Provides
    @Singleton
    fun provideGetLastWeekBMIRecordsUseCase(
        bmiRepository: IBMIRepository,
        bmiEntityToChartBMIModelMapper: IBMIEntityToChartBMIModelMapper
    ): IGetLastWeekBMIRecordsUseCase {

        return GetLastWeekBMIRecordsUseCase(
            bmiRepository = bmiRepository,
            bmiEntityToChartBMIModelMapper = bmiEntityToChartBMIModelMapper
        )

    }//end provideGetLastWeekBMIRecordsUseCase


    @Singleton
    @Provides
    fun provideGetLastHistoryRecordsUseCase(
        bmiRepository: IBMIRepository,
        bmiEntityToSimpleBMIModelMapper: IBMIEntityToSimpleBMIModelMapper
    ): IGetLastHistoryRecordsUseCase {

        return GetLastHistoryRecordsUseCase(
            bmiRepository = bmiRepository,
            bmiEntityToSimpleBMIModelMapper = bmiEntityToSimpleBMIModelMapper
        )

    }//end provideGetLastHistoryRecordsUseCase


    @Provides
    @Singleton
    fun provideGetPageHistoryRecordsUseCase(
        bmiRepository: IBMIRepository,
        heartRateEntityToSimpleHeartRateModelMapper: IBMIEntityToSimpleBMIModelMapper
    ): IGetPageHistoryRecordsUseCase {

        return GetPageHistoryRecordsUseCase(
            bmiRepository = bmiRepository,
            heartRateEntityToSimpleHeartRateModelMapper = heartRateEntityToSimpleHeartRateModelMapper
        )

    }//end provideGetPageHistoryRecordsUseCase


    @Provides
    @Singleton
    fun provideLogoutFromLocalDatabaseUseCase(
        bmiRepository: IBMIRepository,
        heartRateEntityToSimpleHeartRateModelMapper: IBMIEntityToSimpleBMIModelMapper
    ): ILogoutFromLocalDatabaseUseCase {

        return LogoutFromLocalDatabaseUseCase(
            bmiRepository = bmiRepository
        )

    }//end provideGetPageHistoryRecordsUseCase

}//end UseCasesModule