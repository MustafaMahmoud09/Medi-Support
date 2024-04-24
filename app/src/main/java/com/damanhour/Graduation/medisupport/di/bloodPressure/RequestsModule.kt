package com.damanhour.Graduation.medisupport.di.bloodPressure

import com.example.blood.pressure.data.source.remote.data.requests.BloodPressureRequest
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
    fun provideBloodPressureRequest(
        @Named("retrofit_with_token") retrofit: Retrofit
    ): BloodPressureRequest {

        return retrofit.create(BloodPressureRequest::class.java)

    }//end provideBloodPressureRequest


}//end RequestsModule