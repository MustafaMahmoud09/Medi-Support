package com.damanhour.Graduation.medisupport.di.auth

import com.example.auth.domain.usecase.declarations.ICreateNewUserUseCase
import com.example.auth.domain.usecase.declarations.IResetPasswordUseCase
import com.example.auth.domain.usecase.declarations.ISendUserEmailUseCase
import com.example.auth.domain.usecase.declarations.IVerifyCodeUseCase
import com.example.auth.domain.usecase.execution.SendUserEmailUseCase
import com.example.auth.presentation.uiState.viewModel.ForgottenViewModel
import com.example.auth.presentation.uiState.viewModel.RegisterViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelsModule {

    @Provides
    fun provideRegisterViewModel(
        createNewUserUseCase: ICreateNewUserUseCase
    ): RegisterViewModel {

        return RegisterViewModel(
            createNewUserUseCase = createNewUserUseCase
        )

    }//end provideRegisterViewModel

    @Provides
    @Singleton
    fun provideForgottenViewModel(
        resetPasswordUseCase: IResetPasswordUseCase,
        sendUserEmailUseCase: ISendUserEmailUseCase,
        verifyCodeUseCase: IVerifyCodeUseCase
    ): ForgottenViewModel {

        return ForgottenViewModel(
            resetPasswordUseCase = resetPasswordUseCase,
            sendUserEmailUseCase = sendUserEmailUseCase,
            verifyCodeUseCase = verifyCodeUseCase
        )

    }//end provideRegisterViewModel


}//end ViewModelsModule