package com.damanhour.Graduation.medisupport.di.bmi

import com.example.blood.sugar.domain.mapper.declarations.child.IBMIDtoToBMIEntityMapper
import com.example.blood.sugar.domain.mapper.declarations.child.IBMIEntityToAdviceBMIModelMapper
import com.example.blood.sugar.domain.mapper.declarations.child.IBMIEntityToChartBMIModelMapper
import com.example.blood.sugar.domain.mapper.declarations.child.IBMIEntityToSimpleBMIModelMapper
import com.example.bmi.mapper.execution.BMIDtoToBMIEntityMapper
import com.example.bmi.mapper.execution.BMIEntityToAdviceBMIModelMapper
import com.example.bmi.mapper.execution.BMIEntityToChartBMIModelMapper
import com.example.bmi.mapper.execution.BMIEntityToSimpleBMIModelMapper
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
    fun provideBMIDtoToBMIEntityMapper()
            : IBMIDtoToBMIEntityMapper =
        BMIDtoToBMIEntityMapper()


    @Provides
    @Singleton
    fun provideBMIEntityToAdviceBMIModelMapper()
            : IBMIEntityToAdviceBMIModelMapper =
        BMIEntityToAdviceBMIModelMapper()


    @Provides
    @Singleton
    fun provideBMIEntityToChartBMIModelMapper()
            : IBMIEntityToChartBMIModelMapper =
        BMIEntityToChartBMIModelMapper()


    @Provides
    @Singleton
    fun provideBMIEntityToSimpleBMIModelMapper()
            : IBMIEntityToSimpleBMIModelMapper =
        BMIEntityToSimpleBMIModelMapper()

}//end MappersModule