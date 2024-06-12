package com.damanhour.Graduation.medisupport.di.notification

import com.example.notification.data.source.remote.data.requests.NotificationRequest
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
    fun provideNotificationRequest(
        @Named("retrofit_with_token") retrofit: Retrofit
    ): NotificationRequest {

        return retrofit.create(NotificationRequest::class.java)

    }//end provideAccountSettingRequest


}//end RequestsModule