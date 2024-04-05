package com.damanhour.Graduation.medisupport.di.reminder

import com.example.database_creator.MediSupportDatabase
import com.example.reminder.data.repository.ReminderRepositoryImpl
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import com.example.repository.interfaces.IReminderRepository
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
    fun provideReminderRepository(
        sharedPreferencesAccessObject: SharedPreferencesAccessObject,
        localDatabase: MediSupportDatabase
    ): IReminderRepository {

        return ReminderRepositoryImpl(
            sharedPreferencesAccessObject = sharedPreferencesAccessObject,
            localDatabase = localDatabase
        )

    }//end provideReminderRepository


}//end RepositoryModule