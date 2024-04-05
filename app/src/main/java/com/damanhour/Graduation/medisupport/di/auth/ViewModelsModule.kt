package com.damanhour.Graduation.medisupport.di.auth

import com.example.auth.domain.usecase.declarations.ICreateNewUserUseCase
import com.example.auth.presentation.uiState.viewModel.RegisterViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

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

}//end ViewModelsModule