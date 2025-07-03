package com.damanhour.Graduation.medisupport.di.chat

import com.example.chat.data.source.remote.data.requests.ChatRequest
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
    fun provideChatRequest(
        @Named("retrofit_with_token") retrofit: Retrofit
    ): ChatRequest {

        return retrofit.create(ChatRequest::class.java)

    }//end provideBMIRequest


}//end RequestsModule