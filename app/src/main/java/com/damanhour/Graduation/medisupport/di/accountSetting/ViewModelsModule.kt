package com.damanhour.Graduation.medisupport.di.accountSetting

import android.content.Context
import com.example.account.setting.domain.usecase.declarations.IDeleteAccountUseCase
import com.example.account.setting.domain.usecase.declarations.IGetProfileInfoUseCase
import com.example.account.setting.domain.usecase.declarations.ILogoutUseCase
import com.example.account.setting.domain.usecase.declarations.ISendContactUsMessageUseCase
import com.example.account.setting.domain.usecase.declarations.IUpdateProfileInfoUseCase
import com.example.setting.presentation.uiState.viewModel.ContactUsViewModel
import com.example.setting.presentation.uiState.viewModel.MoreViewModel
import com.example.setting.presentation.uiState.viewModel.ProfileViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelsModule {

    @Provides
    fun provideProfileViewModel(
        updateProfileInfoUseCase: IUpdateProfileInfoUseCase,
        getProfileInfoUseCase: IGetProfileInfoUseCase,
        @ApplicationContext context: Context
    ): ProfileViewModel {

        return ProfileViewModel(
            updateProfileInfoUseCase = updateProfileInfoUseCase,
            getProfileInfoUseCase = getProfileInfoUseCase,
            context = context
        )

    }//end provideProfileViewModel


    @Provides
    fun provideContactUsViewModel(
        sendContactUsMessageUseCase: ISendContactUsMessageUseCase
    ): ContactUsViewModel {

        return ContactUsViewModel(
            sendContactUsMessageUseCase = sendContactUsMessageUseCase
        )

    }//end provideContactUsViewModel


    @Provides
    fun provideMoreViewModel(
        deleteAccountUseCase: IDeleteAccountUseCase,
        logoutUseCase: ILogoutUseCase
    ): MoreViewModel {

        return MoreViewModel(
            deleteAccountUseCase = deleteAccountUseCase,
            logoutUseCase = logoutUseCase
        )

    }//end provideMoreViewModel

}//end ViewModelsModule