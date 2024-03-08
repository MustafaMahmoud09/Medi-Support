package com.damanhour.Graduation.medisupport.di.reminder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Singleton
    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext context: Context,
        @Named("chanel_id") chanelId: String
    ): NotificationCompat.Builder {

        return NotificationCompat
            .Builder(context, chanelId)

    }//end provideNotificationBuilder


    @Singleton
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context,
        @Named("chanel_id") chanelId: String,
        @Named("chanel_name") chanelName: String
    ): NotificationManagerCompat {

        val notificationManager = NotificationManagerCompat.from(context)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                chanelId,
                chanelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        return notificationManager

    }//end provideNotificationManager


    @Singleton
    @Provides
    @Named("chanel_id")
    fun provideChanelId(): String {

        return "REMINDER CHANEL ID"

    }//end provideChanelId


    @Singleton
    @Provides
    @Named("chanel_name")
    fun provideChanelName(): String {

        return "Reminder chanel"

    }//end provideChanelId

}//end NotificationModule