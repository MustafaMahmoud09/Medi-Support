package com.damanhour.Graduation.medisupport.di.bloodPressure

import com.example.blood.pressure.data.repository.execution.BloodPressureRepositoryHelper
import com.example.blood.pressure.data.repository.execution.BloodPressureRepositoryImpl
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
        bloodPressureRepositoryHelper: BloodPressureRepositoryHelper,
        sharedPreferencesAccessObject: SharedPreferencesAccessObject,
        localDatabase: MediSupportDatabase,
    ): IBloodPressureRepository {

        return BloodPressureRepositoryImpl(
            bloodPressureRequest = bloodPressureRequest,
            wrapper = wrapper,
            localDatabase = localDatabase,
            bloodPressureRepositoryHelper = bloodPressureRepositoryHelper,
            sharedPreferencesAccessObject = sharedPreferencesAccessObject
        )

    }//end provideBloodPressureRepository


    @Provides
    @Singleton
    fun provideBloodPressureRepositoryHelper(
        localDatabase: MediSupportDatabase,
        latestBloodPressureDtoToBloodPressureEntityMapper: ILatestBloodPressureDtoToBloodPressureEntityMapper
    ): BloodPressureRepositoryHelper {

        return BloodPressureRepositoryHelper(
            localDatabase = localDatabase,
            latestBloodPressureDtoToBloodPressureEntityMapper = latestBloodPressureDtoToBloodPressureEntityMapper
        )

    }//end provideBloodPressureRepositoryHelper

}//end RepositoriesModule