package com.damanhour.Graduation.medisupport.di.notification

import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.notification.data.repository.execution.NotificationRepositoryImpl
import com.example.notification.data.source.remote.data.requests.NotificationRequest
import com.example.notification.domain.repository.declarations.INotificationRepository
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
    fun provideNotificationRepository(
        wrapper: ResponseWrapper,
        notificationRequest: NotificationRequest,
    ): INotificationRepository {

        return NotificationRepositoryImpl(
            wrapper = wrapper,
            notificationRequest = notificationRequest
        )

    }//end provideOfflineBookingRepository


}//end RepositoriesModule