package com.damanhour.Graduation.medisupport.di.auth

import com.example.auth.domain.usecase.declarations.ICheckUserAuthUseCase
import com.example.auth.domain.usecase.declarations.ICreateNewUserUseCase
import com.example.auth.domain.usecase.declarations.ILoginWithEmailUseCase
import com.example.auth.domain.usecase.declarations.ILoginWithSocialUseCase
import com.example.auth.domain.usecase.declarations.IResetPasswordUseCase
import com.example.auth.domain.usecase.declarations.ISendUserEmailUseCase
import com.example.auth.domain.usecase.declarations.IUpdateUsersAuthCountUseCase
import com.example.auth.domain.usecase.declarations.IVerifyCodeUseCase
import com.example.auth.presentation.uiState.viewModel.ForgottenViewModel
import com.example.auth.presentation.uiState.viewModel.LoginViewModel
import com.example.auth.presentation.uiState.viewModel.RegisterViewModel
import com.example.auth.presentation.uiState.viewModel.SplashViewModel
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
        createNewUserUseCase: ICreateNewUserUseCase,
        loginWithFacebookUseCase: ILoginWithSocialUseCase
    ): RegisterViewModel {

        return RegisterViewModel(
            createNewUserUseCase = createNewUserUseCase,
            loginWithSocialUseCase = loginWithFacebookUseCase
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


    @Provides
    fun provideLoginViewModel(
        loginWithEmailUserCase: ILoginWithEmailUseCase,
        loginWithFacebookUseCase: ILoginWithSocialUseCase
    ): LoginViewModel {

        return LoginViewModel(
            loginWithEmailUserCase = loginWithEmailUserCase,
            loginWithSocialUseCase = loginWithFacebookUseCase
        )

    }//end provideLoginViewModel


    @Provides
    fun provideSplashViewModel(
        checkUserAuthUseCase: ICheckUserAuthUseCase,
        updateUsersAuthCountUseCase: IUpdateUsersAuthCountUseCase
    ): SplashViewModel {

        return SplashViewModel(
            checkUserAuthUseCase = checkUserAuthUseCase,
            updateUsersAuthCountUseCase = updateUsersAuthCountUseCase
        )

    }//end provideSplashViewModel

}//end ViewModelsModule