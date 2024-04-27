package com.damanhour.Graduation.medisupport.di.bloodSugar

import com.example.blood.pressure.data.repository.execution.BloodPressureRepositoryHelper
import com.example.blood.pressure.domain.mapper.declarations.child.ILatestBloodPressureDtoToBloodPressureEntityMapper
import com.example.blood.sugar.data.repository.execution.BloodSugarRepositoryHelper
import com.example.blood.sugar.data.repository.execution.BloodSugarRepositoryImpl
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
        bloodSugarRepositoryHelper: BloodSugarRepositoryHelper,
        sharedPreferencesAccessObject: SharedPreferencesAccessObject,
        localDatabase: MediSupportDatabase,
    ): IBloodSugarRepository {

        return BloodSugarRepositoryImpl(
            bloodSugarRequest = bloodSugarRequest,
            wrapper = wrapper,
            localDatabase = localDatabase,
            bloodSugarRepositoryHelper = bloodSugarRepositoryHelper,
            sharedPreferencesAccessObject = sharedPreferencesAccessObject
        )

    }//end provideBloodPressureRepository


    @Provides
    @Singleton
    fun provideBloodSugarRepositoryHelper(
        localDatabase: MediSupportDatabase,
        bloodSugarDtoToBloodSugarEntityMapper: IBloodSugarDtoToBloodSugarEntityMapper
    ): BloodSugarRepositoryHelper {

        return BloodSugarRepositoryHelper(
            localDatabase = localDatabase,
            bloodSugarDtoToBloodSugarEntityMapper = bloodSugarDtoToBloodSugarEntityMapper
        )

    }//end provideBloodSugarRepositoryHelper

}//end RepositoriesModule