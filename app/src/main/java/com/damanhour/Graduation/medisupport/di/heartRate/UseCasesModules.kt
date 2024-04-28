package com.damanhour.Graduation.medisupport.di.heartRate

import com.example.blood.sugar.domain.mapper.declarations.child.IHeartRateEntityToAdviceHeartRateModelMapper
import com.example.blood.sugar.domain.mapper.declarations.child.IHeartRateEntityToChartHeartRateModelMapper
import com.example.blood.sugar.domain.mapper.declarations.child.IHeartRateEntityToSimpleHeartRateModelMapper
import com.example.heart.rate.domain.repository.declarations.IHeartRateRepository
import com.example.heart.rate.domain.usecase.declarations.IAddNewHeartRateRecordUseCase
import com.example.heart.rate.domain.usecase.declarations.ICheckPPGTechnologySupportedUseCase
import com.example.heart.rate.domain.usecase.declarations.IGetLastHistoryRecordsUseCase
import com.example.heart.rate.domain.usecase.declarations.IGetLastWeekHeartRateRecordsUseCase
import com.example.heart.rate.domain.usecase.declarations.IGetLatestHeartRateMeasurementUseCase
import com.example.heart.rate.domain.usecase.declarations.IGetPageHistoryRecordsUseCase
import com.example.heartrate.domain.usecase.AddNewHeartRateRecordUseCase
import com.example.heartrate.domain.usecase.CheckPPGTechnologySupportedUseCase
import com.example.heartrate.domain.usecase.GetLastHistoryRecordsUseCase
import com.example.heartrate.domain.usecase.GetLastWeekHeartRateRecordsUseCase
import com.example.heartrate.domain.usecase.GetLatestHeartRateMeasurementUseCase
import com.example.heartrate.domain.usecase.GetPageHistoryRecordsUseCase
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


    @Provides
    @Singleton
    fun provideAddNewHeartRateRecordUseCase(
        heartRateRepository: IHeartRateRepository
    ): IAddNewHeartRateRecordUseCase {

        return AddNewHeartRateRecordUseCase(
            heartRateRepository = heartRateRepository
        )

    }//end provideAddNewHeartRateRecordUseCase


    @Provides
    @Singleton
    fun provideGetLatestHeartRateMeasurementUseCase(
        heartRateRepository: IHeartRateRepository,
        heartRateEntityToAdviceHeartRateModelMapper: IHeartRateEntityToAdviceHeartRateModelMapper
    ): IGetLatestHeartRateMeasurementUseCase {

        return GetLatestHeartRateMeasurementUseCase(
            heartRateRepository = heartRateRepository,
            heartRateEntityToAdviceHeartRateModelMapper = heartRateEntityToAdviceHeartRateModelMapper
        )

    }//end provideGetLatestHeartRateMeasurementUseCase


    @Provides
    @Singleton
    fun provideGetLastWeekHeartRateRecordsUseCase(
        heartRateRepository: IHeartRateRepository,
        heartRateEntityToChartHeartRateModelMapper: IHeartRateEntityToChartHeartRateModelMapper
    ): IGetLastWeekHeartRateRecordsUseCase {

        return GetLastWeekHeartRateRecordsUseCase(
            heartRateRepository = heartRateRepository,
            heartRateEntityToChartHeartRateModelMapper = heartRateEntityToChartHeartRateModelMapper
        )

    }//end provideGetLastWeekHeartRateRecordsUseCase


    @Provides
    @Singleton
    fun provideGetLastHistoryRecordsUseCase(
        heartRateRepository: IHeartRateRepository,
        heartRateEntityToSimpleHeartRateModelMapper: IHeartRateEntityToSimpleHeartRateModelMapper
    ): IGetLastHistoryRecordsUseCase {

        return GetLastHistoryRecordsUseCase(
            heartRateRepository = heartRateRepository,
            heartRateEntityToSimpleHeartRateModelMapper = heartRateEntityToSimpleHeartRateModelMapper
        )

    }//end GetLastHistoryRecordsUseCase


    @Singleton
    @Provides
    fun provideGetPageHistoryRecordsUseCase(
        heartRateRepository: IHeartRateRepository,
        heartRateEntityToSimpleHeartRateModelMapper: IHeartRateEntityToSimpleHeartRateModelMapper
    ): IGetPageHistoryRecordsUseCase {

        return GetPageHistoryRecordsUseCase(
            heartRateRepository = heartRateRepository,
            heartRateEntityToSimpleHeartRateModelMapper = heartRateEntityToSimpleHeartRateModelMapper
        )

    }//end provideGetPageHistoryRecordsUseCase

}//end UseCasesModules