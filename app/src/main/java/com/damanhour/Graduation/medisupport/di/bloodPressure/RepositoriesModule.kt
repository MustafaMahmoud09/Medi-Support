package com.damanhour.Graduation.medisupport.di.bloodPressure

import com.example.blood.pressure.data.repository.execution.cacheHelperExecution.CacheBloodPressureRepositoryHelper
import com.example.blood.pressure.data.repository.execution.BloodPressureRepositoryImpl
import com.example.blood.pressure.data.repository.execution.cacheHelperDeclarations.ICacheBloodPressureRepositoryHelper
import com.example.blood.pressure.data.repository.execution.cacheHelperDeclarations.IServerBloodPressureRepositoryHelper
import com.example.blood.pressure.data.repository.execution.cacheHelperExecution.ServerBloodPressureRepositoryHelper
import com.example.blood.pressure.data.source.remote.data.requests.BloodPressureRequest
import com.example.blood.pressure.domain.mapper.declarations.child.ILatestBloodPressureDtoToBloodPressureEntityMapper
import com.example.blood.pressure.domain.repository.declarations.IBloodPressureRepository
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
    fun provideBloodPressureRepository(
        bloodPressureRequest: BloodPressureRequest,
        wrapper: ResponseWrapper,
        localDatabase: MediSupportDatabase,
        sharedPreferencesAccessObject: SharedPreferencesAccessObject,
        serverBloodPressureRepositoryHelper: IServerBloodPressureRepositoryHelper
    ): IBloodPressureRepository {

        return BloodPressureRepositoryImpl(
            bloodPressureRequest = bloodPressureRequest,
            wrapper = wrapper,
            localDatabase = localDatabase,
            serverBloodPressureRepositoryHelper = serverBloodPressureRepositoryHelper,
            sharedPreferencesAccessObject = sharedPreferencesAccessObject
        )

    }//end provideBloodPressureRepository


    @Provides
    @Singleton
    fun provideServerBloodPressureRepositoryHelper(
        bloodPressureRequest: BloodPressureRequest,
        wrapper: ResponseWrapper,
        cacheBloodPressureRepositoryHelper: ICacheBloodPressureRepositoryHelper,
    ): IServerBloodPressureRepositoryHelper{

        return ServerBloodPressureRepositoryHelper(
            bloodPressureRequest = bloodPressureRequest,
            wrapper = wrapper,
            cacheBloodPressureRepositoryHelper = cacheBloodPressureRepositoryHelper
        )

    }//end provideServerBloodPressureRepositoryHelper


    @Provides
    @Singleton
    fun provideBloodPressureRepositoryHelper(
        localDatabase: MediSupportDatabase,
        latestBloodPressureDtoToBloodPressureEntityMapper: ILatestBloodPressureDtoToBloodPressureEntityMapper
    ): ICacheBloodPressureRepositoryHelper {

        return CacheBloodPressureRepositoryHelper(
            localDatabase = localDatabase,
            latestBloodPressureDtoToBloodPressureEntityMapper = latestBloodPressureDtoToBloodPressureEntityMapper
        )

    }//end provideBloodPressureRepositoryHelper

}//end RepositoriesModule