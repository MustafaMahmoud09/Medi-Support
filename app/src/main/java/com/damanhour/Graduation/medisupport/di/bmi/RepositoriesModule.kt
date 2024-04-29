package com.damanhour.Graduation.medisupport.di.bmi

import com.example.blood.sugar.domain.mapper.declarations.child.IBMIDtoToBMIEntityMapper
import com.example.bmi.data.repository.execution.BMIRepositoryImpl
import com.example.bmi.data.repository.execution.cacheHelperDeclarations.ICacheBMIRepositoryHelper
import com.example.bmi.data.repository.execution.cacheHelperDeclarations.IServerBMIRepositoryHelper
import com.example.bmi.data.repository.execution.cacheHelperExecution.CacheBMIRepositoryHelper
import com.example.bmi.data.repository.execution.cacheHelperExecution.ServerBMIRepositoryHelper
import com.example.bmi.data.source.remote.data.requests.BMIRequest
import com.example.bmi.domain.repository.declarations.IBMIRepository
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideBMIRepository(
        wrapper: ResponseWrapper,
        bmiRequest: BMIRequest,
        serverBMIHelper: IServerBMIRepositoryHelper,
        localDatabase: MediSupportDatabase,
        sharedPreferencesAccessObject: SharedPreferencesAccessObject
    ): IBMIRepository {

        return BMIRepositoryImpl(
            bmiRequest = bmiRequest,
            wrapper = wrapper,
            localDatabase = localDatabase,
            serverBMIHelper = serverBMIHelper,
            sharedPreferencesAccessObject = sharedPreferencesAccessObject
        )

    }//end provideBMIRepository


    @Provides
    @Singleton
    fun provideServerBMIRepositoryHelper (
        bmiRequest: BMIRequest,
        wrapper: ResponseWrapper,
        cacheBMIRepositoryHelper: ICacheBMIRepositoryHelper,
    ): IServerBMIRepositoryHelper {

        return ServerBMIRepositoryHelper (
            bmiRequest = bmiRequest,
            wrapper = wrapper,
            cacheBMIRepositoryHelper = cacheBMIRepositoryHelper
        )

    }//end provideServerBMIRepositoryHelper


    @Provides
    @Singleton
    fun provideCacheBMIRepositoryHelper (
        localDatabase: MediSupportDatabase,
        bmiDtoToBMIEntityMapper: IBMIDtoToBMIEntityMapper
    ): ICacheBMIRepositoryHelper {

        return CacheBMIRepositoryHelper (
            localDatabase = localDatabase,
            bmiDtoToBMIEntityMapper = bmiDtoToBMIEntityMapper
        )

    }//end provideServerBMIRepositoryHelper

}//end RepositoriesModule