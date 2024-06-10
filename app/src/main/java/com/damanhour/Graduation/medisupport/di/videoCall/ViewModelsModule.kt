package com.damanhour.Graduation.medisupport.di.videoCall

import android.content.Context
import com.example.room.presentation.uiState.viewModel.OnlineRoomViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelsModule {


    @Provides
    fun provideOnlineRoomViewModel(
        @ApplicationContext context: Context
    ): OnlineRoomViewModel {

        return OnlineRoomViewModel(
            context = context
        )

    }//end provideReminderServiceViewModel


}//end ViewModelsModule