package com.damanhour.Graduation.medisupport.di.videoCall

import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.room.data.repository.execution.OnlineRoomRepositoryImpl
import com.example.room.data.source.remote.data.requests.OnlineRoomRequest
import com.example.room.domain.repository.declarations.IOnlineRoomRepository
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
    fun provideOnlineRoomRepository(
        wrapper: ResponseWrapper,
        onlineRoomRequest: OnlineRoomRequest,
        localDatabase: MediSupportDatabase,
    ): IOnlineRoomRepository {

        return OnlineRoomRepositoryImpl(
            wrapper = wrapper,
            onlineRoomRequest = onlineRoomRequest,
            localDatabase = localDatabase
        )

    }//end provideOnlineBookingRepository

}//end RepositoriesModule