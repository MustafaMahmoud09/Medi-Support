package com.damanhour.Graduation.medisupport.di.heartRate

import com.example.blood.sugar.domain.mapper.declarations.child.IHeartRateDtoToHeartRateEntityMapper
import com.example.blood.sugar.domain.mapper.declarations.child.IHeartRateEntityToAdviceHeartRateModelMapper
import com.example.blood.sugar.domain.mapper.declarations.child.IHeartRateEntityToChartHeartRateModelMapper
import com.example.blood.sugar.domain.mapper.declarations.child.IHeartRateEntityToSimpleHeartRateModelMapper
import com.example.heart.rate.mapper.execution.HeartRateDtoToHeartRateEntityMapper
import com.example.heart.rate.mapper.execution.HeartRateEntityToAdviceHeartRateModelMapper
import com.example.heart.rate.mapper.execution.HeartRateEntityToChartHeartRateModelMapper
import com.example.heart.rate.mapper.execution.HeartRateEntityToSimpleHeartRateModelMapper
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
    fun provideHeartRateDtoToHeartRateEntityMapper()
            : IHeartRateDtoToHeartRateEntityMapper =
        HeartRateDtoToHeartRateEntityMapper()


    @Provides
    @Singleton
    fun provideHeartRateEntityToAdviceHeartRateModelMapper()
            : IHeartRateEntityToAdviceHeartRateModelMapper =
        HeartRateEntityToAdviceHeartRateModelMapper()


    @Provides
    @Singleton
    fun provideHeartRateEntityToChartHeartRateModelMapper()
            : IHeartRateEntityToChartHeartRateModelMapper =
        HeartRateEntityToChartHeartRateModelMapper()


    @Provides
    @Singleton
    fun provideHeartRateEntityToSimpleHeartRateModelMapper()
            : IHeartRateEntityToSimpleHeartRateModelMapper =
        HeartRateEntityToSimpleHeartRateModelMapper()

}//end MappersModule