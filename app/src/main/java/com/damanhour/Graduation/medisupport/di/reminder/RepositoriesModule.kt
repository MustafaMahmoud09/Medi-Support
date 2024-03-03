package com.damanhour.Graduation.medisupport.di.reminder

import android.content.Context
import com.example.localdata.MediSupportDatabase
import com.example.reminder.data.repository.ReminderRepositoryImpl
import com.example.reminder.data.source.shared.preferences.ReminderPreferencesAccess
import com.example.repository.interfaces.IReminderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideReminderRepository(
        reminderPreferencesAccess: ReminderPreferencesAccess,
        localDatabase: MediSupportDatabase
    ): IReminderRepository {

        return ReminderRepositoryImpl(
            reminderPreferencesAccess = reminderPreferencesAccess,
            localDatabase = localDatabase
        )

    }//end provideReminderRepository


    @Provides
    @Singleton
    fun provideReminderPreferencesAccess(
        @ApplicationContext context: Context
    ): ReminderPreferencesAccess {

        return ReminderPreferencesAccess(
            context = context
        )

    }//end provideReminderPreferencesAccess

}//end RepositoryModule