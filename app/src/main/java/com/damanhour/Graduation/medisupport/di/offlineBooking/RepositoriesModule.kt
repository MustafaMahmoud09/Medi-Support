package com.damanhour.Graduation.medisupport.di.offlineBooking

import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.offline.bookin.data.source.remote.data.requests.OfflineBookingRequest
import com.example.offline.bookin.data.source.remote.data.requests.OfflineDoctorsRequest
import com.example.offline.booking.data.repository.execution.OfflineBookingRepositoryImpl
import com.example.offline.booking.domain.repository.declarations.IOfflineBookingRepository
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
        offlineBookingRequest: OfflineBookingRequest
    ): IOfflineBookingRepository {

        return OfflineBookingRepositoryImpl(
            wrapper = wrapper,
            offlineDoctorsRequest = offlineDoctorsRequest,
            offlineBookingRequest = offlineBookingRequest
        )

    }//end provideOfflineBookingRepository


}//end RepositoriesModule