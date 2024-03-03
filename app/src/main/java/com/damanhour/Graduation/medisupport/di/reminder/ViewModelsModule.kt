package com.damanhour.Graduation.medisupport.di.reminder

import com.example.reminder.domain.usecase.interfaces.IAddDaysUseCase
import com.example.reminder.domain.usecase.interfaces.IGetDaysUseCase
import com.example.reminder.presentation.uiState.viewModel.AddReminderViewModel
import com.example.reminder.presentation.uiState.viewModel.ReminderViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelsModule {


    @Provides
    @Singleton
    fun provideReminderViewModel(): ReminderViewModel {

        return ReminderViewModel()

    }//end provideReminderViewModel

    @Provides
    @Singleton
    fun provideAddReminderViewModel(
        addDaysUseCase: IAddDaysUseCase,
        getDaysUseCase: IGetDaysUseCase
    ): AddReminderViewModel {

        return AddReminderViewModel(
            addDaysUseCase = addDaysUseCase,
            getDaysUseCase = getDaysUseCase
        )

    }//end provideAddReminderViewModel

}//end ViewModelsModule