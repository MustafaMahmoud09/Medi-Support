package com.damanhour.Graduation.medisupport.di.notification

import com.example.notification.domain.usecase.declarations.IGetPageNotificationsUseCase
import com.example.notification.domain.usecase.declarations.IReadAllNotificationUseCase
import com.example.notification.domain.usecase.declarations.IReadNotificationUseCase
import com.example.notification.presentation.uiState.viewModel.NotificationViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelsModule {

    @Provides
    fun provideNotificationViewModel(
        getPageNotificationsUseCase: IGetPageNotificationsUseCase,
        readNotificationUseCase: IReadNotificationUseCase,
        readAllNotificationUseCase: IReadAllNotificationUseCase
    ): NotificationViewModel {

        return NotificationViewModel(
            getPageNotificationsUseCase = getPageNotificationsUseCase,
            readNotificationUseCase = readNotificationUseCase,
            readAllNotificationUseCase = readAllNotificationUseCase
        )

    }//end provideProfileViewModel

}//end ViewModelsModule