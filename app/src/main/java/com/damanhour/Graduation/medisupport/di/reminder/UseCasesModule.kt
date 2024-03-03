package com.damanhour.Graduation.medisupport.di.reminder

import com.example.reminder.domain.mapper.declarations.child.IDayEntityToDayModelMapper
import com.example.reminder.domain.usecase.AddDaysUseCase
import com.example.reminder.domain.usecase.CheckAppFirstRunUseCase
import com.example.reminder.domain.usecase.GetDaysUseCase
import com.example.reminder.domain.usecase.interfaces.IAddDaysUseCase
import com.example.reminder.domain.usecase.interfaces.ICheckAppFirstRunUseCase
import com.example.reminder.domain.usecase.interfaces.IGetDaysUseCase
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

}//end UseCaseModule