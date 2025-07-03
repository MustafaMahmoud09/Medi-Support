package com.damanhour.Graduation.medisupport.di.chat

import com.example.chat.domain.usecase.declarations.IGetChannelAuthTokenUseCase
import com.example.chat.domain.usecase.declarations.IGetPageChatsUseCase
import com.example.chat.domain.usecase.declarations.IGetAuthUserInfoUseCase
import com.example.presentation.uiState.viewModel.ChatsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object ViewModelsModule {

    @Provides
    fun provideChatsViewModel(
        getChatsUseCase: IGetPageChatsUseCase,
        getChannelAuthTokenUseCase: IGetChannelAuthTokenUseCase,
        @Named("cluster") cluster: String,
        @Named("pusher_key") pusherKey: String,
        @Named("domain") baseUrl: String,
        getProfileInfoUseCase: IGetAuthUserInfoUseCase
    ): ChatsViewModel {

        return ChatsViewModel(
            getChatsUseCase = getChatsUseCase,
            getChannelAuthTokenUseCase = getChannelAuthTokenUseCase,
            cluster = cluster,
            pusherKey = pusherKey,
            getProfileInfoUseCase = getProfileInfoUseCase,
            baseUrl = baseUrl
        )

    }//end provideRecordBMIViewModel


}//end ViewModelsModule