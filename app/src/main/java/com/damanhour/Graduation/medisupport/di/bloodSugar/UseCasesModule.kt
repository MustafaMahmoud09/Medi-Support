package com.damanhour.Graduation.medisupport.di.bloodSugar

import com.example.blood.sugar.domain.mapper.declarations.child.IBloodPressureStatusDtoToStatusModelMapper
import com.example.blood.sugar.domain.mapper.declarations.child.IBloodSugarEntityToAdviceBloodSugarModelMapper
import com.example.blood.sugar.domain.mapper.declarations.child.IBloodSugarEntityToChartBloodSugarModelMapper
import com.example.blood.sugar.domain.mapper.declarations.child.IBloodSugarEntityToSimpleBloodSugarModelMapper
import com.example.blood.sugar.domain.repository.declarations.IBloodSugarRepository
import com.example.blood.sugar.domain.usecase.declarations.IAddNewBloodSugarRecordUseCase
import com.example.blood.sugar.domain.usecase.declarations.IGetBloodSugarStatusUseCase
import com.example.blood.sugar.domain.usecase.declarations.IGetLastHistoryRecordsUseCase
import com.example.blood.sugar.domain.usecase.declarations.IGetLastWeekBloodSugarRecordsUseCase
import com.example.blood.sugar.domain.usecase.declarations.IGetLatestBloodSugarMeasurementUseCase
import com.example.blood.sugar.domain.usecase.declarations.IGetPageHistoryRecordsUseCase
import com.example.blood.sugar.domain.usecase.declarations.ILogoutFromLocalDatabaseUseCase
import com.example.blood.sugar.domain.usecase.execution.AddNewBloodSugarRecordUseCase
import com.example.blood.sugar.domain.usecase.execution.GetBloodSugarStatusUseCase
import com.example.blood.sugar.domain.usecase.execution.GetLastHistoryRecordsUseCase
import com.example.blood.sugar.domain.usecase.execution.GetLastWeekBloodSugarRecordsUseCase
import com.example.blood.sugar.domain.usecase.execution.GetLatestBloodSugarMeasurementUseCase
import com.example.blood.sugar.domain.usecase.execution.GetPageHistoryRecordsUseCase
import com.example.blood.sugar.domain.usecase.execution.LogoutFromLocalDatabaseUseCase
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
    fun provideAddNewBloodSugarRecordUseCase(
        bloodSugarRepository: IBloodSugarRepository
    ): IAddNewBloodSugarRecordUseCase {

        return AddNewBloodSugarRecordUseCase(
            bloodSugarRepository = bloodSugarRepository,
        )

    }//end provideAddNewBloodSugarRecordUseCase


    @Provides
    @Singleton
    fun provideGetBloodSugarStatusUseCase(
        bloodSugarRepository: IBloodSugarRepository,
        bloodPressureStatusDtoToStatusModelMapper: IBloodPressureStatusDtoToStatusModelMapper
    ): IGetBloodSugarStatusUseCase {

        return GetBloodSugarStatusUseCase(
            bloodSugarRepository = bloodSugarRepository,
            bloodPressureStatusDtoToStatusModelMapper = bloodPressureStatusDtoToStatusModelMapper
        )

    }//end provideGetBloodSugarStatusUseCase


    @Singleton
    @Provides
    fun provideGetLatestBloodSugarMeasurementUseCase(
        bloodSugarRepository: IBloodSugarRepository,
        bloodSugarEntityToAdviceBloodSugarModelMapper: IBloodSugarEntityToAdviceBloodSugarModelMapper
    ): IGetLatestBloodSugarMeasurementUseCase {

        return GetLatestBloodSugarMeasurementUseCase(
            bloodSugarRepository = bloodSugarRepository,
            bloodSugarEntityToAdviceBloodSugarModelMapper = bloodSugarEntityToAdviceBloodSugarModelMapper
        )

    }//end provideGetLatestBloodSugarMeasurementUseCase


    @Provides
    @Singleton
    fun provideGetLastWeekBloodSugarRecordsUseCase(
        bloodSugarRepository: IBloodSugarRepository,
        bloodSugarEntityToChartBloodSugarModelMapper: IBloodSugarEntityToChartBloodSugarModelMapper
    ): IGetLastWeekBloodSugarRecordsUseCase {

        return GetLastWeekBloodSugarRecordsUseCase(
            bloodSugarRepository = bloodSugarRepository,
            bloodSugarEntityToChartBloodSugarModelMapper = bloodSugarEntityToChartBloodSugarModelMapper
        )

    }//end provideGetLastWeekBloodSugarRecordsUseCase


    @Provides
    @Singleton
    fun provideGetLastHistoryRecordsUseCase(
        bloodSugarRepository: IBloodSugarRepository,
        bloodSugarEntityToSimpleBloodSugarModelMapper: IBloodSugarEntityToSimpleBloodSugarModelMapper
    ): IGetLastHistoryRecordsUseCase {

        return GetLastHistoryRecordsUseCase(
            bloodSugarRepository = bloodSugarRepository,
            bloodSugarEntityToSimpleBloodSugarModelMapper = bloodSugarEntityToSimpleBloodSugarModelMapper
        )

    }//end provideGetLastHistoryRecordsUseCase


    //getPageHistoryRecordsUseCase
    @Provides
    @Singleton
    fun provideGetPageHistoryRecordsUseCase(
        bloodSugarRepository: IBloodSugarRepository,
        bloodSugarEntityToSimpleBloodSugarModelMapper: IBloodSugarEntityToSimpleBloodSugarModelMapper
    ): IGetPageHistoryRecordsUseCase {

        return GetPageHistoryRecordsUseCase(
            bloodSugarRepository = bloodSugarRepository,
            bloodSugarEntityToSimpleBloodSugarModelMapper = bloodSugarEntityToSimpleBloodSugarModelMapper
        )

    }//end provideGetPageHistoryRecordsUseCase


    @Provides
    @Singleton
    fun provideLogoutFromLocalDatabaseUseCase(
        bloodSugarRepository: IBloodSugarRepository,
        bloodSugarEntityToSimpleBloodSugarModelMapper: IBloodSugarEntityToSimpleBloodSugarModelMapper
    ): ILogoutFromLocalDatabaseUseCase {

        return LogoutFromLocalDatabaseUseCase(
            bloodSugarRepository = bloodSugarRepository
        )

    }//end provideGetPageHistoryRecordsUseCase


}//end UseCasesModule