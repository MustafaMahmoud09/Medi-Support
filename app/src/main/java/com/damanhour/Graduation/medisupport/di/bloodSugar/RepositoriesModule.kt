package com.damanhour.Graduation.medisupport.di.bloodSugar

import com.example.blood.sugar.data.repository.execution.BloodSugarRepositoryImpl
import com.example.blood.sugar.data.repository.execution.cacheHelperDeclarations.ICacheBloodSugarRepositoryHelper
import com.example.blood.sugar.data.repository.execution.cacheHelperDeclarations.IServerBloodSugarRepositoryHelper
import com.example.blood.sugar.data.repository.execution.cacheHelperExecution.CacheBloodSugarRepositoryHelper
import com.example.blood.sugar.data.repository.execution.cacheHelperExecution.ServerBloodSugarRepositoryHelper
import com.example.blood.sugar.data.source.remote.data.requests.BloodSugarRequest
import com.example.blood.sugar.domain.mapper.declarations.child.IBloodSugarDtoToBloodSugarEntityMapper
import com.example.blood.sugar.domain.repository.declarations.IBloodSugarRepository
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
    fun provideBloodSugarRepository(
        bloodSugarRequest: BloodSugarRequest,
        wrapper: ResponseWrapper,
        serverBloodSugarRepositoryHelper: IServerBloodSugarRepositoryHelper,
        sharedPreferencesAccessObject: SharedPreferencesAccessObject,
        localDatabase: MediSupportDatabase,
    ): IBloodSugarRepository {

        return BloodSugarRepositoryImpl(
            bloodSugarRequest = bloodSugarRequest,
            wrapper = wrapper,
            localDatabase = localDatabase,
            serverBloodSugarRepositoryHelper = serverBloodSugarRepositoryHelper,
            sharedPreferencesAccessObject = sharedPreferencesAccessObject
        )

    }//end provideBloodPressureRepository


    @Provides
    @Singleton
    fun provideServerBloodSugarRepositoryHelper(
        localDatabase: MediSupportDatabase,
        wrapper: ResponseWrapper,
        bloodSugarRequest: BloodSugarRequest,
        cacheBloodSugarRepositoryHelper: ICacheBloodSugarRepositoryHelper,
    ): IServerBloodSugarRepositoryHelper {

        return ServerBloodSugarRepositoryHelper(
            localDatabase = localDatabase,
            wrapper = wrapper,
            bloodSugarRequest = bloodSugarRequest,
            cacheBloodSugarRepositoryHelper = cacheBloodSugarRepositoryHelper
        )

    }//end provideServerBloodSugarRepositoryHelper


    @Provides
    @Singleton
    fun provideBloodSugarRepositoryHelper(
        localDatabase: MediSupportDatabase,
        bloodSugarDtoToBloodSugarEntityMapper: IBloodSugarDtoToBloodSugarEntityMapper
    ): ICacheBloodSugarRepositoryHelper {

        return CacheBloodSugarRepositoryHelper(
            localDatabase = localDatabase,
            bloodSugarDtoToBloodSugarEntityMapper = bloodSugarDtoToBloodSugarEntityMapper
        )

    }//end provideBloodSugarRepositoryHelper

}//end RepositoriesModule