package com.damanhour.Graduation.medisupport.di.reminder

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.reminder.domain.usecase.interfaces.IAddDaysUseCase
import com.example.reminder.domain.usecase.interfaces.IAddReminderUseCase
import com.example.reminder.domain.usecase.interfaces.IDeleteReminderUseCase
import com.example.reminder.domain.usecase.interfaces.IGetDaysUseCase
import com.example.reminder.domain.usecase.interfaces.IGetUserRemindersUseCase
import com.example.reminder.domain.usecase.interfaces.IUpdateReminderStatusUseCase
import com.example.reminder.presentation.uiState.viewModel.AddReminderViewModel
import com.example.reminder.presentation.uiState.viewModel.RemindersViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@RequiresApi(Build.VERSION_CODES.O)
@Module
@InstallIn(SingletonComponent::class)
object ViewModelsModule {


    @Provides
    fun provideReminderViewModel(
        getUserRemindersUseCase: IGetUserRemindersUseCase,
        updateReminderStatusUseCase: IUpdateReminderStatusUseCase,
        deleteReminderUseCase: IDeleteReminderUseCase
    ): RemindersViewModel {

        return RemindersViewModel(
            getUserRemindersUseCase = getUserRemindersUseCase,
            updateReminderStatusUseCase = updateReminderStatusUseCase,
            deleteReminderUseCase = deleteReminderUseCase
        )

    }//end provideReminderViewModel


    @Provides
    fun provideAddReminderViewModel(
        addDaysUseCase: IAddDaysUseCase,
        getDaysUseCase: IGetDaysUseCase,
        addReminderUseCase: IAddReminderUseCase
    ): AddReminderViewModel {

        return AddReminderViewModel(
            addDaysUseCase = addDaysUseCase,
            getDaysUseCase = getDaysUseCase,
            addReminderUseCase = addReminderUseCase
        )

    }//end provideAddReminderViewModel

}//end ViewModelsModule