package com.damanhour.Graduation.medisupport.di.reminder

import com.example.reminder.domain.mapper.declarations.child.IDayEntityToDayModelMapper
import com.example.reminder.domain.mapper.declarations.child.IReminderWithDaysEntityToReminderModelMapper
import com.example.reminder.mapper.DayEntityToDayModelMapper
import com.example.reminder.mapper.ReminderWithDaysEntityToReminderModelMapper
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


    @Provides
    @Singleton
    fun provideReminderWithDaysEntityToReminderModelMapper(
        dayEntityToDayModelMapper: IDayEntityToDayModelMapper
    ): IReminderWithDaysEntityToReminderModelMapper{

        return ReminderWithDaysEntityToReminderModelMapper(
            dayEntityToDayModelMapper = dayEntityToDayModelMapper
        )

    }//end IReminderWithDaysEntityToReminderModelMapper

}//end MappersModule