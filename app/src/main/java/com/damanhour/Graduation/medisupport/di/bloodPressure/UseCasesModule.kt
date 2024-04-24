package com.damanhour.Graduation.medisupport.di.bloodPressure

import com.example.blood.pressure.domain.mapper.declarations.child.IBloodPressureEntityToAdviceBloodPressureModelMapper
import com.example.blood.pressure.domain.mapper.declarations.child.IDescBloodPressureDtoToChartBloodPressureModelMapper
import com.example.blood.pressure.domain.repository.declarations.IBloodPressureRepository
import com.example.blood.pressure.domain.usecase.declarations.IAddBloodPressureRecordUseCase
import com.example.blood.pressure.domain.usecase.declarations.IGetLastWeekDiastolicRecordsUseCase
import com.example.blood.pressure.domain.usecase.declarations.IGetLastWeekSystolicRecordsUseCase
import com.example.blood.pressure.domain.usecase.declarations.IGetLatestBloodPressureMeasurementUserCase
import com.example.blood.pressure.domain.usecase.execution.AddBloodPressureRecordUseCase
import com.example.blood.pressure.domain.usecase.execution.GetLastWeekDiastolicRecordsUseCase
import com.example.blood.pressure.domain.usecase.execution.GetLastWeekSystolicRecordsUseCase
import com.example.blood.pressure.domain.usecase.execution.GetLatestBloodPressureMeasurementUserCase
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
    fun provideAddBloodPressureRecordUseCase(
        bloodPressureRepository: IBloodPressureRepository
    ): IAddBloodPressureRecordUseCase {

        return AddBloodPressureRecordUseCase(
            bloodPressureRepository = bloodPressureRepository,
        )

    }//end provideAddBloodPressureRecordUseCase


    @Provides
    @Singleton
    fun provideGetLatestBloodPressureMeasurementUserCase(
        bloodPressureRepository: IBloodPressureRepository,
        bloodPressureEntityToAdviceBloodPressureModelMapper: IBloodPressureEntityToAdviceBloodPressureModelMapper
    ): IGetLatestBloodPressureMeasurementUserCase {

        return GetLatestBloodPressureMeasurementUserCase(
            bloodPressureRepository = bloodPressureRepository,
            bloodPressureEntityToAdviceBloodPressureModelMapper = bloodPressureEntityToAdviceBloodPressureModelMapper
        )

    }//end provideGetLatestBloodPressureMeasurementUserCase


    @Provides
    @Singleton
    fun provideGetLastWeekSystolicRecordsUseCase(
        bloodPressureRepository: IBloodPressureRepository,
        descBloodPressureDtoToChartBloodPressureModelMapper: IDescBloodPressureDtoToChartBloodPressureModelMapper
    ): IGetLastWeekSystolicRecordsUseCase {

        return GetLastWeekSystolicRecordsUseCase(
            bloodPressureRepository = bloodPressureRepository,
            descBloodPressureDtoToChartBloodPressureModelMapper = descBloodPressureDtoToChartBloodPressureModelMapper
        )

    }//end provideGetLastWeekSystolicRecordsUseCase


    @Provides
    @Singleton
    fun provideGetLastWeekDiastolicRecordsUseCas(
        bloodPressureRepository: IBloodPressureRepository,
        descBloodPressureDtoToChartBloodPressureModelMapper: IDescBloodPressureDtoToChartBloodPressureModelMapper
    ): IGetLastWeekDiastolicRecordsUseCase {

        return GetLastWeekDiastolicRecordsUseCase(
            bloodPressureRepository = bloodPressureRepository,
            descBloodPressureDtoToChartBloodPressureModelMapper = descBloodPressureDtoToChartBloodPressureModelMapper
        )

    }//end provideGetLastWeekSystolicRecordsUseCase


}//end UseCasesModule