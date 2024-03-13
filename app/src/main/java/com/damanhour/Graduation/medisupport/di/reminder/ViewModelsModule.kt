package com.damanhour.Graduation.medisupport.di.reminder

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.reminder.domain.usecase.interfaces.IAddDaysUseCase
import com.example.reminder.domain.usecase.interfaces.IAddReminderUseCase
import com.example.reminder.domain.usecase.interfaces.IDeleteReminderUseCase
import com.example.reminder.domain.usecase.interfaces.IGetActiveRemindersSizeUseCase
import com.example.reminder.domain.usecase.interfaces.IGetDaysUseCase
import com.example.reminder.domain.usecase.interfaces.IGetNearestRemindersUseCase
import com.example.reminder.domain.usecase.interfaces.IGetReminderServiceRunningStateUseCase
import com.example.reminder.domain.usecase.interfaces.IGetUserRemindersUseCase
import com.example.reminder.domain.usecase.interfaces.ISetReminderServiceRunningStateUseCase
import com.example.reminder.domain.usecase.interfaces.IUpdateReminderStatusUseCase
import com.example.reminder.presentation.uiState.viewModel.AddReminderViewModel
import com.example.reminder.presentation.uiState.viewModel.ReminderServiceViewModel
import com.example.reminder.presentation.uiState.viewModel.RemindersViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
        addReminderUseCase: IAddReminderUseCase,
        getReminderActiveSizeUseCase: IGetActiveRemindersSizeUseCase,
        setReminderServiceRunningStateUseCase: ISetReminderServiceRunningStateUseCase,
        getReminderServiceRunningStateUseCase: IGetReminderServiceRunningStateUseCase,
        @ApplicationContext context: Context
    ): AddReminderViewModel {

        return AddReminderViewModel(
            addDaysUseCase = addDaysUseCase,
            getDaysUseCase = getDaysUseCase,
            addReminderUseCase = addReminderUseCase,
            getReminderActiveSizeUseCase = getReminderActiveSizeUseCase,
            setReminderServiceRunningStateUseCase = setReminderServiceRunningStateUseCase,
            getReminderServiceRunningStateUseCase = getReminderServiceRunningStateUseCase,
            context = context
        )

    }//end provideAddReminderViewModel


    @Provides
    @Singleton
    fun provideReminderServiceViewModel(
        getNearestRemindersUseCase: IGetNearestRemindersUseCase,
        notificationBuilder: NotificationCompat.Builder,
        notificationManager: NotificationManagerCompat,
        @ApplicationContext context: Context
    ): ReminderServiceViewModel {

        return ReminderServiceViewModel(
            getNearestRemindersUseCase = getNearestRemindersUseCase,
            notificationBuilder = notificationBuilder,
            notificationManager = notificationManager,
            context = context
        )

    }//end provideReminderServiceViewModel


}//end ViewModelsModule