package com.damanhour.Graduation.medisupport.di.heartRate

import android.content.Context
import com.example.heart.rate.domain.repository.declarations.IHeartRateRepository
import com.example.heartrate.data.repository.HeartRateRepository
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
    fun provideHeartRateRepository(
        @ApplicationContext context: Context
    ): IHeartRateRepository{

        return HeartRateRepository(
            context = context
        )

    }//end provideHeartRateRepository

}//end RepositoriesModule