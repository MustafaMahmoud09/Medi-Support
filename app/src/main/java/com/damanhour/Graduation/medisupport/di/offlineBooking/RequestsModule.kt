package com.damanhour.Graduation.medisupport.di.offlineBooking

import com.example.offline.bookin.data.source.remote.data.requests.OfflineBookingRequest
import com.example.offline.bookin.data.source.remote.data.requests.OfflineDoctorsRequest
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
    fun provideOfflineDoctorsRequest(
        @Named("retrofit_with_token") retrofit: Retrofit
    ): OfflineDoctorsRequest {

        return retrofit.create(OfflineDoctorsRequest::class.java)

    }//end provideOfflineDoctorsRequest


    @Provides
    @Singleton
    fun provideOfflineBookingRequest(
        @Named("retrofit_with_token") retrofit: Retrofit
    ): OfflineBookingRequest {

        return retrofit.create(OfflineBookingRequest::class.java)

    }//end provideOfflineDoctorsRequest


}//end RequestsModule