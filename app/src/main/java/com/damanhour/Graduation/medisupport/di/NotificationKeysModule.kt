package com.damanhour.Graduation.medisupport.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationKeysModule {

    @Provides
    @Singleton
    @Named("reminder_notification_id")
    fun provideReminderNotificationId(): Int = 100

    @Provides
    @Singleton
    @Named("foreground_reminder_notification_id")
    fun provideForegroundReminderNotificationId(): Int = 200

}//end NotificationKeysModule