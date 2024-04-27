package com.damanhour.Graduation.medisupport.di.bloodSugar

import com.example.blood.sugar.domain.mapper.declarations.child.IBloodPressureStatusDtoToStatusModelMapper
import com.example.blood.sugar.domain.mapper.declarations.child.IBloodSugarDtoToBloodSugarEntityMapper
import com.example.blood.sugar.domain.mapper.declarations.child.IBloodSugarEntityToAdviceBloodSugarModelMapper
import com.example.blood.sugar.domain.mapper.declarations.child.IBloodSugarEntityToChartBloodSugarModelMapper
import com.example.blood.sugar.domain.mapper.declarations.child.IBloodSugarEntityToSimpleBloodSugarModelMapper
import com.example.blood.sugar.mapper.execution.BloodPressureStatusDtoToStatusModelMapper
import com.example.blood.sugar.mapper.execution.BloodSugarDtoToBloodSugarEntityMapper
import com.example.blood.sugar.mapper.execution.BloodSugarEntityToAdviceBloodSugarModelMapper
import com.example.blood.sugar.mapper.execution.BloodSugarEntityToChartBloodSugarModelMapper
import com.example.blood.sugar.mapper.execution.BloodSugarEntityToSimpleBloodSugarModelMapper
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
    fun provideBloodPressureStatusDtoToStatusModelMapper()
            : IBloodPressureStatusDtoToStatusModelMapper =
        BloodPressureStatusDtoToStatusModelMapper()


    @Provides
    @Singleton
    fun provideBloodSugarEntityToAdviceBloodSugarModelMapper()
            : IBloodSugarEntityToAdviceBloodSugarModelMapper =
        BloodSugarEntityToAdviceBloodSugarModelMapper()


    @Provides
    @Singleton
    fun provideBloodSugarEntityToChartBloodSugarModelMapper()
            : IBloodSugarEntityToChartBloodSugarModelMapper =
        BloodSugarEntityToChartBloodSugarModelMapper()


    @Provides
    @Singleton
    fun provideBloodSugarDtoToBloodSugarEntityMapper()
            : IBloodSugarDtoToBloodSugarEntityMapper =
        BloodSugarDtoToBloodSugarEntityMapper()


    @Provides
    @Singleton
    fun provideBloodSugarEntityToSimpleBloodSugarModelMapper()
            : IBloodSugarEntityToSimpleBloodSugarModelMapper =
        BloodSugarEntityToSimpleBloodSugarModelMapper()

}//end MappersModule