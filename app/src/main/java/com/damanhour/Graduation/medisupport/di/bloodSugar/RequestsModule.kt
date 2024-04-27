package com.damanhour.Graduation.medisupport.di.bloodSugar

import com.example.blood.sugar.data.source.remote.data.requests.BloodSugarRequest
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
    fun provideBloodSugarRequest(
        @Named("retrofit_with_token") retrofit: Retrofit
    ): BloodSugarRequest {

        return retrofit.create(BloodSugarRequest::class.java)

    }//end provideBloodSugarRequest


}//end RequestsModule