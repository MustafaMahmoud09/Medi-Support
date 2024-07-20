package com.damanhour.Graduation.medisupport.di.chat

import android.content.Context
import com.example.chat.data.repository.execution.ChatRepositoryImpl
import com.example.chat.data.source.remote.data.requests.ChatRequest
import com.example.chat.domain.repository.declarations.IChatRepository
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideChatRepository(
        wrapper: ResponseWrapper,
        chatRequest: ChatRequest,
        localDatabase: MediSupportDatabase,
        @ApplicationContext context: Context,
        sharedPreferencesAccessObject: SharedPreferencesAccessObject,
    ): IChatRepository {

        return ChatRepositoryImpl(
            wrapper = wrapper,
            chatRequest = chatRequest,
            localDatabase = localDatabase,
            context = context,
            sharedPreferencesAccessObject = sharedPreferencesAccessObject
        )

    }//end provideBMIRepository

}//end RepositoriesModule