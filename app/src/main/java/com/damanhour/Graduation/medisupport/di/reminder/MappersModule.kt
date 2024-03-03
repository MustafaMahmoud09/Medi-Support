package com.damanhour.Graduation.medisupport.di.reminder

import com.example.reminder.domain.mapper.declarations.child.IDayEntityToDayModelMapper
import com.example.reminder.mapper.DayEntityToDayModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    @Singleton
    fun provideDayEntityToDayModelMapper(): IDayEntityToDayModelMapper {

        return DayEntityToDayModelMapper()

    }//end IDayEntityToDayModelMapper

}//end MappersModule