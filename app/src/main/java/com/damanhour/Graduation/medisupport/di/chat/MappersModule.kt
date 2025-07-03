package com.damanhour.Graduation.medisupport.di.chat

import com.example.account.setting.domain.mapper.declarations.child.IUserEntityToUserModelMapper
import com.example.account.setting.mapper.execution.UserEntityToUserModelMapper
import com.example.chat.domain.mapper.declarations.child.IChatDtoToChatModelMapper
import com.example.chat.domain.mapper.declarations.child.IDoctorDtoToDoctorModelMapper
import com.example.chat.domain.mapper.declarations.child.IMessageDtoToMessageModelMapper
import com.example.chat.mapper.execution.ChatDtoToChatModelMapper
import com.example.chat.mapper.execution.DoctorDtoToDoctorModelMapper
import com.example.chat.mapper.execution.MessageDtoToMessageModelMapper
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
    fun provideChatDtoToChatModelMapper(
        @Named("domain") imageUrl: String
    ): IChatDtoToChatModelMapper {

        return ChatDtoToChatModelMapper(
            baseUrl = imageUrl
        )

    }//end provideUserEntityToUserModelMapper


    @Provides
    @Singleton
    fun provideDoctorDtoToDoctorModelMapper(
        @Named("domain") imageUrl: String
    ): IDoctorDtoToDoctorModelMapper {

        return DoctorDtoToDoctorModelMapper(
            baseUrl = imageUrl
        )

    }//end provideUserEntityToUserModelMapper


    //
    @Provides
    @Singleton
    fun provideMessageDtoToMessageModelMapper(
        @Named("domain") imageUrl: String
    ): IMessageDtoToMessageModelMapper {

        return MessageDtoToMessageModelMapper(
            baseUrl = imageUrl
        )

    }//end provideUserEntityToUserModelMapper

}//end MappersModule