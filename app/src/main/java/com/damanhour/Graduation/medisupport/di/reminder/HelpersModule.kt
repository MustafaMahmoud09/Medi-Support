package com.damanhour.Graduation.medisupport.di.reminder

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.reminder.presentation.uiState.viewModel.reminderService.helperDeclarations.IReminderNotificationHelper
import com.example.reminder.presentation.uiState.viewModel.reminderService.helperExecution.ReminderNotificationHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HelpersModule {

    @Provides
    @Singleton
    fun provideReminderNotificationHelper(
        notificationBuilder: NotificationCompat.Builder,
        notificationManager: NotificationManagerCompat,
        @ApplicationContext context: Context,
        @Named("reminder_notification_id") reminderNotificationId: Int,
    ): IReminderNotificationHelper{

        return ReminderNotificationHelper(
            context = context,
            notificationBuilder = notificationBuilder,
            notificationManager = notificationManager,
            reminderNotificationId = reminderNotificationId

        )

    }//end provideReminderNotificationHelper

}//end HelpersModule