package com.damanhour.Graduation.medisupport.di

import android.content.Context
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {


    @Provides
    @Singleton
    fun provideReminderPreferencesAccess(
        @ApplicationContext context: Context
    ): SharedPreferencesAccessObject {

        return SharedPreferencesAccessObject(
            context = context
        )

    }//end provideReminderPreferencesAccess

}//end SharedPreferencesModule