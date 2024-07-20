package com.damanhour.Graduation.medisupport.di.chat

import com.example.chat.domain.mapper.declarations.child.IChatDtoToChatModelMapper
import com.example.chat.domain.mapper.declarations.child.IDoctorDtoToDoctorModelMapper
import com.example.chat.domain.mapper.declarations.child.IMessageDtoToMessageModelMapper
import com.example.chat.domain.repository.declarations.IChatRepository
import com.example.chat.domain.usecase.declarations.IGetChannelAuthTokenUseCase
import com.example.chat.domain.usecase.declarations.IGetDoctorUseCase
import com.example.chat.domain.usecase.declarations.IGetPageChatsUseCase
import com.example.chat.domain.usecase.declarations.IGetPageMessageUseCase
import com.example.chat.domain.usecase.declarations.IGetAuthUserInfoUseCase
import com.example.chat.domain.usecase.declarations.ISeenChatMessagesUseCase
import com.example.chat.domain.usecase.declarations.ISendChatMessageUseCase
import com.example.chat.domain.usecase.execution.GetChannelAuthTokenUseCase
import com.example.chat.domain.usecase.execution.GetDoctorUseCase
import com.example.chat.domain.usecase.execution.GetPageChatsUseCase
import com.example.chat.domain.usecase.execution.GetPageMessageUseCase
import com.example.chat.domain.usecase.execution.GetAuthUserInfoUseCase
import com.example.chat.domain.usecase.execution.SeenChatMessagesUseCase
import com.example.chat.domain.usecase.execution.SendChatMessageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {


    @Provides
    @Singleton
    fun provideGetPageChatsUseCase(
        chatRepository: IChatRepository,
        chatDtoToChatModelMapper: IChatDtoToChatModelMapper
    ): IGetPageChatsUseCase {

        return GetPageChatsUseCase(
            chatRepository = chatRepository,
            chatDtoToChatModelMapper = chatDtoToChatModelMapper
        )

    }//end provideAddNewBMIRecordUseCase


    @Provides
    @Singleton
    fun provideGetPageMessageUseCase(
        chatRepository: IChatRepository,
        messageDtoToMessageModelMapper: IMessageDtoToMessageModelMapper
    ): IGetPageMessageUseCase {

        return GetPageMessageUseCase(
            chatRepository = chatRepository,
            messageDtoToMessageModelMapper = messageDtoToMessageModelMapper
        )

    }//end provideAddNewBMIRecordUseCase


    @Provides
    @Singleton
    fun provideGetDoctorUseCase(
        chatRepository: IChatRepository,
        doctorDtoToDoctorModelMapper: IDoctorDtoToDoctorModelMapper
    ): IGetDoctorUseCase {

        return GetDoctorUseCase(
            chatRepository = chatRepository,
            doctorDtoToDoctorModelMapper = doctorDtoToDoctorModelMapper

        )

    }//end provideAddNewBMIRecordUseCase


    @Provides
    @Singleton
    fun provideGetChannelAuthTokenUseCase(
        chatRepository: IChatRepository
    ): IGetChannelAuthTokenUseCase {

        return GetChannelAuthTokenUseCase(
            chatRepository = chatRepository
        )

    }//end provideGetChannelAuthTokenUseCase


    @Provides
    @Singleton
    fun provideGetProfileInfoUseCase(
        chatRepository: IChatRepository
    ): IGetAuthUserInfoUseCase {

        return GetAuthUserInfoUseCase(
            chatRepository = chatRepository
        )

    }//end provideGetChannelAuthTokenUseCase


    @Provides
    @Singleton
    fun provideSeenChatMessagesUseCase(
        chatRepository: IChatRepository
    ): ISeenChatMessagesUseCase {

        return SeenChatMessagesUseCase(
            chatRepository = chatRepository
        )

    }//end provideGetChannelAuthTokenUseCase


    @Provides
    @Singleton
    fun provideSendChatMessageUseCase(
        chatRepository: IChatRepository,
        @Named("domain") imageUrl: String
    ): ISendChatMessageUseCase {

        return SendChatMessageUseCase(
            chatRepository = chatRepository,
            baseUrl = imageUrl
        )

    }//end provideGetChannelAuthTokenUseCase

}//end UseCasesModule