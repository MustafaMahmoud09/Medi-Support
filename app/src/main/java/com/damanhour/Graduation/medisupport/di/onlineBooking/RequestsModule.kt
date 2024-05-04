package com.damanhour.Graduation.medisupport.di.onlineBooking

import com.example.online.booking.data.source.remote.data.requests.OnlineBookingRequest
import com.example.online.booking.data.source.remote.data.requests.OnlineDoctorsRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RequestsModule {


    @Provides
    @Singleton
    fun provideOnlineDoctorsRequest(
        @Named("retrofit_with_token") retrofit: Retrofit
    ): OnlineDoctorsRequest {

        return retrofit.create(OnlineDoctorsRequest::class.java)

    }//end provideOnlineDoctorsRequest


    @Provides
    @Singleton
    fun provideOnlineBookingRequest(
        @Named("retrofit_with_token") retrofit: Retrofit
    ): OnlineBookingRequest {

        return retrofit.create(OnlineBookingRequest::class.java)

    }//end provideOnlineBookingRequest

}//end RequestsModule