package com.damanhour.Graduation.medisupport.di.notification

import com.example.notification.domain.mapper.declarations.child.INotificationDtoToNotificationModelMapper
import com.example.notification.mapper.execution.NotificationDtoToNotificationModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    @Singleton
    fun provideNotificationDtoToNotificationModelMapper(
        @Named("domain") imageUrl: String
    ): INotificationDtoToNotificationModelMapper {

        return NotificationDtoToNotificationModelMapper()

    }//end provideUserEntityToUserModelMapper


}//end MappersModule