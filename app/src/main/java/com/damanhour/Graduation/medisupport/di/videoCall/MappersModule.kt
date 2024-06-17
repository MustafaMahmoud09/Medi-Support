package com.damanhour.Graduation.medisupport.di.videoCall

import com.example.room.domain.mapper.declarations.child.IOnlineRoomDtoToOnlineRoomModelMapper
import com.example.room.mapper.execution.OnlineRoomDtoToOnlineRoomModelMapper
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
    fun provideOnlineBookingEntityToOnlineBookingModelMapper()
            : IOnlineRoomDtoToOnlineRoomModelMapper =
        OnlineRoomDtoToOnlineRoomModelMapper()


}//end MappersModule