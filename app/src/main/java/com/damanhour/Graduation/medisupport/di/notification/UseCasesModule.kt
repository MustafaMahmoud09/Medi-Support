package com.damanhour.Graduation.medisupport.di.notification

import com.example.notification.domain.mapper.declarations.child.INotificationDtoToNotificationModelMapper
import com.example.notification.domain.repository.declarations.INotificationRepository
import com.example.notification.domain.usecase.declarations.IGetPageNotificationsUseCase
import com.example.notification.domain.usecase.declarations.IReadAllNotificationUseCase
import com.example.notification.domain.usecase.declarations.IReadNotificationUseCase
import com.example.notification.domain.usecase.execution.GetPageNotificationsUseCase
import com.example.notification.domain.usecase.execution.ReadAllNotificationUseCase
import com.example.notification.domain.usecase.execution.ReadNotificationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {


    @Provides
    @Singleton
    fun provideGetPageNotificationsUseCase(
        notificationRepository: INotificationRepository,
        notificationDtoToNotificationModelMapper: INotificationDtoToNotificationModelMapper
    ): IGetPageNotificationsUseCase {

        return GetPageNotificationsUseCase(
            notificationRepository = notificationRepository,
            notificationDtoToNotificationModelMapper = notificationDtoToNotificationModelMapper
        )

    }//end provideUpdateProfileInfoUseCase


    @Provides
    @Singleton
    fun provideReadNotificationUseCase(
        notificationRepository: INotificationRepository
    ): IReadNotificationUseCase {

        return ReadNotificationUseCase(
            notificationRepository = notificationRepository
        )

    }//end provideReadNotificationUseCase


    @Provides
    @Singleton
    fun provideReadAllNotificationUseCase(
        notificationRepository: INotificationRepository
    ): IReadAllNotificationUseCase {

        return ReadAllNotificationUseCase(
            notificationRepository = notificationRepository
        )

    }//end provideReadAllNotificationUseCase


}//end UseCasesModule