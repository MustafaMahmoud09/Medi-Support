package com.damanhour.Graduation.medisupport.di.onlineBooking

import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.online.booking.data.repository.execution.OnlineBookingRepositoryImpl
import com.example.online.booking.data.source.remote.data.requests.OnlineBookingRequest
import com.example.online.booking.data.source.remote.data.requests.OnlineDoctorsRequest
import com.example.online.booking.domain.repository.declarations.IOnlineBookingRepository
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
    ): IOnlineBookingRepository {

        return OnlineBookingRepositoryImpl(
            wrapper = wrapper,
            onlineDoctorsRequest = onlineDoctorsRequest,
            onlineBookingRequest = onlineBookingRequest
        )

    }//end provideOnlineBookingRepository


}//end RepositoriesModule