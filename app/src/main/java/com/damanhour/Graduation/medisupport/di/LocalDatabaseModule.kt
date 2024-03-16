package com.damanhour.Graduation.medisupport.di

import android.content.Context
import androidx.room.Room
import com.example.database_creator.MediSupportDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {

    @Provides
    @Singleton
    fun provideMediSupportDatabase(
        @ApplicationContext context: Context,
        appDatabase: Class<MediSupportDatabase>,
        @Named("local_database_name") databaseName: String,
    ): MediSupportDatabase {

        return synchronized(this) {
            Room.databaseBuilder(
                context = context,
                klass = appDatabase,
                name = databaseName
            ).build()
        }

    }//end provideMediSupportDatabase

    @Provides
    @Singleton
    fun provideAppDatabase(): Class<MediSupportDatabase> {

        return MediSupportDatabase::class.java

    }//end provideAppDatabase

    @Provides
    @Singleton
    @Named("local_database_name")
    fun provideDatabaseName(): String {

        return "medi_support_database"

    }//end provideDatabaseName

}//end LocalDatabaseModule