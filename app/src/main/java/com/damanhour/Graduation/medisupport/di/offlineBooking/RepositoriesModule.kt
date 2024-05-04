package com.damanhour.Graduation.medisupport.di.offlineBooking

import com.example.blood.sugar.data.repository.execution.cacheHelperExecution.CacheOfflineBookingRepositoryHelper
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.offline.bookin.data.source.remote.data.requests.OfflineBookingRequest
import com.example.offline.bookin.data.source.remote.data.requests.OfflineDoctorsRequest
import com.example.offline.booking.data.repository.execution.OfflineBookingRepositoryImpl
import com.example.offline.booking.data.repository.execution.cacheHelperDeclarations.ICacheOfflineBookingRepositoryHelper
import com.example.offline.booking.data.repository.execution.cacheHelperDeclarations.IServerOfflineBookingRepositoryHelper
import com.example.offline.booking.domain.mapper.declarations.child.IOfflineBookingDtoToOfflineBookingEntityMapper
import com.example.offline.booking.domain.repository.declarations.IOfflineBookingRepository
import com.example.online.booking.data.repository.execution.cacheHelperExecution.ServerOfflineBookingRepositoryHelper
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
    fun provideOfflineBookingRepository(
        offlineDoctorsRequest: OfflineDoctorsRequest,
        wrapper: ResponseWrapper,
        offlineBookingRequest: OfflineBookingRequest,
        sharedPreferencesAccessObject: SharedPreferencesAccessObject,
        serverOfflineBookingRepositoryHelper: IServerOfflineBookingRepositoryHelper,
        localDatabase: MediSupportDatabase
    ): IOfflineBookingRepository {

        return OfflineBookingRepositoryImpl(
            wrapper = wrapper,
            offlineDoctorsRequest = offlineDoctorsRequest,
            offlineBookingRequest = offlineBookingRequest,
            sharedPreferencesAccessObject = sharedPreferencesAccessObject,
            serverOfflineBookingRepositoryHelper = serverOfflineBookingRepositoryHelper,
            localDatabase = localDatabase
        )

    }//end provideOfflineBookingRepository


    @Provides
    @Singleton
    fun provideServerOfflineBookingRepositoryHelper(
        wrapper: ResponseWrapper,
        offlineBookingRequest: OfflineBookingRequest,
        cacheOnlineBookingRepositoryHelper: ICacheOfflineBookingRepositoryHelper,
    ): IServerOfflineBookingRepositoryHelper {

        return ServerOfflineBookingRepositoryHelper(
            wrapper = wrapper,
            offlineBookingRequest = offlineBookingRequest,
            cacheOnlineBookingRepositoryHelper = cacheOnlineBookingRepositoryHelper
        )

    }//end provideServerOfflineBookingRepositoryHelper


    @Provides
    @Singleton
    fun provideCacheOfflineBookingRepositoryHelper(
        localDatabase: MediSupportDatabase,
        offlineDtoToOfflineBookingEntityMapper: IOfflineBookingDtoToOfflineBookingEntityMapper
    ): ICacheOfflineBookingRepositoryHelper {

        return CacheOfflineBookingRepositoryHelper(
            localDatabase = localDatabase,
            offlineDtoToOfflineBookingEntityMapper = offlineDtoToOfflineBookingEntityMapper
        )

    }//end provideCacheOfflineBookingRepositoryHelper

}//end RepositoriesModule