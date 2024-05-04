package com.damanhour.Graduation.medisupport.di.onlineBooking

import com.example.blood.sugar.data.repository.execution.cacheHelperExecution.CacheOnlineBookingRepositoryHelper
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.online.booking.data.repository.execution.OnlineBookingRepositoryImpl
import com.example.online.booking.data.repository.execution.cacheHelperDeclarations.ICacheOnlineBookingRepositoryHelper
import com.example.online.booking.data.repository.execution.cacheHelperDeclarations.IServerOnlineBookingRepositoryHelper
import com.example.online.booking.data.repository.execution.cacheHelperExecution.ServerOnlineBookingRepositoryHelper
import com.example.online.booking.data.source.remote.data.requests.OnlineBookingRequest
import com.example.online.booking.data.source.remote.data.requests.OnlineDoctorsRequest
import com.example.online.booking.domain.mapper.declarations.child.IOnlineBookingDtoToOnlineBookingEntityMapper
import com.example.online.booking.domain.repository.declarations.IOnlineBookingRepository
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
    fun provideOnlineBookingRepository(
        wrapper: ResponseWrapper,
        onlineDoctorsRequest: OnlineDoctorsRequest,
        onlineBookingRequest: OnlineBookingRequest,
        sharedPreferencesAccessObject: SharedPreferencesAccessObject,
        serverOnlineBookingRepositoryHelper: IServerOnlineBookingRepositoryHelper,
        localDatabase: MediSupportDatabase,
    ): IOnlineBookingRepository {

        return OnlineBookingRepositoryImpl(
            wrapper = wrapper,
            onlineDoctorsRequest = onlineDoctorsRequest,
            onlineBookingRequest = onlineBookingRequest,
            sharedPreferencesAccessObject = sharedPreferencesAccessObject,
            serverOnlineBookingRepositoryHelper = serverOnlineBookingRepositoryHelper,
            localDatabase = localDatabase
        )

    }//end provideOnlineBookingRepository


    @Provides
    @Singleton
    fun provideServerOnlineBookingRepositoryHelper(
        wrapper: ResponseWrapper,
        onlineBookingRequest: OnlineBookingRequest,
        cacheOnlineBookingRepositoryHelper: ICacheOnlineBookingRepositoryHelper,
    ): IServerOnlineBookingRepositoryHelper{

        return ServerOnlineBookingRepositoryHelper(
            cacheOnlineBookingRepositoryHelper = cacheOnlineBookingRepositoryHelper,
            onlineBookingRequest = onlineBookingRequest,
            wrapper = wrapper
        )

    }//end provideServerOnlineBookingRepositoryHelper


    @Provides
    @Singleton
    fun provideCacheOnlineBookingRepositoryHelper(
        localDatabase: MediSupportDatabase,
        onlineBookingDtoToOnlineBookingEntityMapper: IOnlineBookingDtoToOnlineBookingEntityMapper
    ): ICacheOnlineBookingRepositoryHelper{

        return CacheOnlineBookingRepositoryHelper(
            localDatabase = localDatabase,
            onlineBookingDtoToOnlineBookingEntityMapper = onlineBookingDtoToOnlineBookingEntityMapper
        )

    }//end provideCacheOnlineBookingRepositoryHelper

}//end RepositoriesModule