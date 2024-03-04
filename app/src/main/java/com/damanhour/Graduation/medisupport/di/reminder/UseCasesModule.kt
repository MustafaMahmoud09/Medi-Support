package com.damanhour.Graduation.medisupport.di.reminder

import com.example.reminder.domain.mapper.declarations.child.IDayEntityToDayModelMapper
import com.example.reminder.domain.mapper.declarations.child.IReminderWithDaysEntityToReminderModelMapper
import com.example.reminder.domain.usecase.AddDaysUseCase
import com.example.reminder.domain.usecase.AddReminderUseCase
import com.example.reminder.domain.usecase.CheckAppFirstRunUseCase
import com.example.reminder.domain.usecase.DeleteReminderUseCase
import com.example.reminder.domain.usecase.GetDaysUseCase
import com.example.reminder.domain.usecase.GetUserRemindersUseCase
import com.example.reminder.domain.usecase.UpdateReminderStatusUseCase
import com.example.reminder.domain.usecase.interfaces.IAddDaysUseCase
import com.example.reminder.domain.usecase.interfaces.IAddReminderUseCase
import com.example.reminder.domain.usecase.interfaces.ICheckAppFirstRunUseCase
import com.example.reminder.domain.usecase.interfaces.IDeleteReminderUseCase
import com.example.reminder.domain.usecase.interfaces.IGetDaysUseCase
import com.example.reminder.domain.usecase.interfaces.IGetUserRemindersUseCase
import com.example.reminder.domain.usecase.interfaces.IUpdateReminderStatusUseCase
import com.example.repository.interfaces.IReminderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideAddDaysUseCase(
        reminderRepository: IReminderRepository,
        checkAppFirstRunUseCase: ICheckAppFirstRunUseCase
    ): IAddDaysUseCase {

        return AddDaysUseCase(
            reminderRepository = reminderRepository,
            checkAppFirstRunUseCase = checkAppFirstRunUseCase
        )

    }//end IAddDaysUseCase

    @Provides
    @Singleton
    fun provideCheckAppFirstRunUseCase(
        reminderRepository: IReminderRepository
    ): ICheckAppFirstRunUseCase {

        return CheckAppFirstRunUseCase(
            reminderRepository = reminderRepository
        )

    }//end provideCheckAppFirstRunUseCase


    @Provides
    @Singleton
    fun provideGetDaysUseCase(
        reminderRepository: IReminderRepository,
        dayEntityToDayModelMapper: IDayEntityToDayModelMapper
    ): IGetDaysUseCase {

        return GetDaysUseCase(
            reminderRepository = reminderRepository,
            dayEntityToDayModelMapper = dayEntityToDayModelMapper
        )

    }//end provideGetDaysUseCase


    @Provides
    @Singleton
    fun provideAddReminderUseCase(
        reminderRepository: IReminderRepository
    ): IAddReminderUseCase {

        return AddReminderUseCase(
            reminderRepository = reminderRepository
        )

    }//end provideAddReminderUseCase


    @Provides
    @Singleton
    fun provideGetUserRemindersUseCase(
        reminderRepository: IReminderRepository,
        reminderEntityToReminderModelMapper: IReminderWithDaysEntityToReminderModelMapper
    ): IGetUserRemindersUseCase {

        return GetUserRemindersUseCase(
            reminderRepository = reminderRepository,
            reminderEntityToReminderModelMapper = reminderEntityToReminderModelMapper
        )

    }//end provideGetUserRemindersUseCase


    @Provides
    @Singleton
    fun provideUpdateReminderStatusUseCase(
        reminderRepository: IReminderRepository,
    ): IUpdateReminderStatusUseCase {

        return UpdateReminderStatusUseCase(
            reminderRepository = reminderRepository
        )

    }//end provideUpdateReminderStatusUseCase


    @Provides
    @Singleton
    fun provideDeleteReminderUseCase(
        reminderRepository: IReminderRepository,
    ): IDeleteReminderUseCase {

        return DeleteReminderUseCase(
            reminderRepository = reminderRepository
        )

    }//end provideDeleteReminderUseCase

}//end UseCaseModule