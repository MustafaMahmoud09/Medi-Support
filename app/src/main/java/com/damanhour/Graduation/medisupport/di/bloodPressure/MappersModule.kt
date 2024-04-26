package com.damanhour.Graduation.medisupport.di.bloodPressure

import com.example.blood.pressure.domain.mapper.declarations.child.IBloodPressureEntityToAdviceBloodPressureModelMapper
import com.example.blood.pressure.domain.mapper.declarations.child.IBloodPressureEntityToSimpleBloodPressureModelMapper
import com.example.blood.pressure.domain.mapper.declarations.child.IDescBloodPressureDtoToChartBloodPressureModelMapper
import com.example.blood.pressure.domain.mapper.declarations.child.ILatestBloodPressureDtoToBloodPressureEntityMapper
import com.example.blood.pressure.mapper.execution.BloodPressureEntityToAdviceBloodPressureModelMapper
import com.example.blood.pressure.mapper.execution.BloodPressureEntityToSimpleBloodPressureModelMapper
import com.example.blood.pressure.mapper.execution.DescBloodPressureDtoToChartBloodPressureModelMapper
import com.example.blood.pressure.mapper.execution.LatestBloodPressureDtoToBloodPressureEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {


    @Provides
    @Singleton
    fun provideBloodPressureEntityToAdviceBloodPressureModelMapper()
            : IBloodPressureEntityToAdviceBloodPressureModelMapper =
        BloodPressureEntityToAdviceBloodPressureModelMapper()


    @Provides
    @Singleton
    fun provideLatestBloodPressureDtoToBloodPressureEntityMapper()
            : ILatestBloodPressureDtoToBloodPressureEntityMapper =
        LatestBloodPressureDtoToBloodPressureEntityMapper()


    @Provides
    @Singleton
    fun provideDescBloodPressureDtoToChartBloodPressureModelMapper()
            : IDescBloodPressureDtoToChartBloodPressureModelMapper =
        DescBloodPressureDtoToChartBloodPressureModelMapper()


    @Provides
    @Singleton
    fun provideBloodPressureEntityToSimpleBloodPressureModelMapper()
            : IBloodPressureEntityToSimpleBloodPressureModelMapper =
        BloodPressureEntityToSimpleBloodPressureModelMapper()

}//end MappersModule