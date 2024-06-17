package com.damanhour.Graduation.medisupport.di.videoCall

import com.example.room.data.source.remote.data.requests.OnlineRoomRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RequestsModule {


    @Provides
    @Singleton
    fun provideOnlineRoomRequest(
        @Named("retrofit_with_token") retrofit: Retrofit
    ): OnlineRoomRequest {

        return retrofit.create(OnlineRoomRequest::class.java)

    }//end provideOnlineDoctorsRequest

}//end RequestsModule