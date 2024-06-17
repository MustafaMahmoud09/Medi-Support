package com.damanhour.Graduation.medisupport.di.videoCall

import com.example.room.domain.mapper.declarations.child.IOnlineRoomDtoToOnlineRoomModelMapper
import com.example.room.domain.repository.declarations.IOnlineRoomRepository
import com.example.room.domain.usecase.declarations.IFinishOnlineRoomUseCase
import com.example.room.domain.usecase.declarations.IGetOnlineRoomUseCase
import com.example.room.domain.usecase.declarations.IStartOnlineRoomUseCase
import com.example.room.domain.usecase.execution.FinishOnlineRoomUseCase
import com.example.room.domain.usecase.execution.GetOnlineRoomUseCase
import com.example.room.domain.usecase.execution.StartOnlineRoomUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {


    @Provides
    @Singleton
    fun provideGetOnlineRoomUseCase(
        onlineRoomRepository: IOnlineRoomRepository,
        onlineRoomDtoToOnlineRoomModelMapper: IOnlineRoomDtoToOnlineRoomModelMapper
    ): IGetOnlineRoomUseCase {

        return GetOnlineRoomUseCase(
            onlineRoomRepository = onlineRoomRepository,
            onlineRoomDtoToOnlineRoomModelMapper = onlineRoomDtoToOnlineRoomModelMapper
        )

    }//end provideGetTopOnlineDoctorsUseCase


    @Provides
    @Singleton
    fun provideFinishOnlineRoomUseCase(
        onlineRoomRepository: IOnlineRoomRepository
    ): IFinishOnlineRoomUseCase {

        return FinishOnlineRoomUseCase(
            onlineRoomRepository = onlineRoomRepository
        )

    }//end provideGetTopOnlineDoctorsUseCase


    @Provides
    @Singleton
    fun provideStartOnlineRoomUseCase(
        onlineRoomRepository: IOnlineRoomRepository
    ): IStartOnlineRoomUseCase {

        return StartOnlineRoomUseCase(
            onlineRoomRepository = onlineRoomRepository
        )

    }//end provideGetTopOnlineDoctorsUseCase

}//end UseCasesModule