package com.damanhour.Graduation.medisupport.di.heartRate

import android.content.Context
import com.example.blood.sugar.domain.mapper.declarations.child.IHeartRateDtoToHeartRateEntityMapper
import com.example.database_creator.MediSupportDatabase
import com.example.heart.rate.data.source.remote.data.requests.HeartRateRequest
import com.example.heart.rate.domain.repository.declarations.IHeartRateRepository
import com.example.heartrate.data.repository.HeartRateRepositoryImpl
import com.example.heartrate.data.repository.cacheHelper.CacheHeartRateRepositoryHelper
import com.example.heartrate.data.repository.cacheHelper.ServerHeartRateRepositoryHelper
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {


    @Provides
    @Singleton
    fun provideHeartRateRepository(
        @ApplicationContext context: Context,
        wrapper: ResponseWrapper,
        heartRateRequest: HeartRateRequest,
        serverHeartRateHelper: ServerHeartRateRepositoryHelper,
        localDatabase: MediSupportDatabase,
        sharedPreferencesAccessObject: SharedPreferencesAccessObject
    ): IHeartRateRepository {

        return HeartRateRepositoryImpl(
            context = context,
            wrapper = wrapper,
            heartRateRequest = heartRateRequest,
            serverHeartRateHelper = serverHeartRateHelper,
            localDatabase = localDatabase,
            sharedPreferencesAccessObject
        )

    }//end provideHeartRateRepository


    @Provides
    @Singleton
    fun provideServerHeartRateHelper(
        heartRateRequest: HeartRateRequest,
        wrapper: ResponseWrapper,
        cacheHeartRateRepositoryHelper: CacheHeartRateRepositoryHelper
    ): ServerHeartRateRepositoryHelper {

        return ServerHeartRateRepositoryHelper(
            heartRateRequest = heartRateRequest,
            wrapper = wrapper,
            cacheHeartRateRepositoryHelper = cacheHeartRateRepositoryHelper
        )

    }//end provideServerHeartRateHelper


    @Provides
    @Singleton
    fun provideCacheHeartRateRepositoryHelper(
        localDatabase: MediSupportDatabase,
        heartRateDtoToHeartRateEntityMapper: IHeartRateDtoToHeartRateEntityMapper
    ): CacheHeartRateRepositoryHelper {

        return CacheHeartRateRepositoryHelper(
            localDatabase = localDatabase,
            heartRateDtoToHeartRateEntityMapper = heartRateDtoToHeartRateEntityMapper
        )

    }//end provideCacheHeartRateRepositoryHelper

}//end RepositoriesModule