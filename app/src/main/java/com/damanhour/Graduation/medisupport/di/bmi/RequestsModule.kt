package com.damanhour.Graduation.medisupport.di.bmi

import com.example.bmi.data.source.remote.data.requests.BMIRequest
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
    fun provideBMIRequest(
        @Named("retrofit_with_token") retrofit: Retrofit
    ): BMIRequest {

        return retrofit.create(BMIRequest::class.java)

    }//end provideBMIRequest


}//end RequestsModule