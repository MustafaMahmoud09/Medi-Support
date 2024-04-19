package com.damanhour.Graduation.medisupport.di.article

import com.example.article.domain.usecase.declarations.IGetPageArticlesUseCase
import com.example.article.presentation.uiState.viewModel.ArticlesViewModel
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
    fun provideArticlesViewModel(
        getPageArticlesUseCase: IGetPageArticlesUseCase
    ): ArticlesViewModel{

        return ArticlesViewModel(
            getPageArticlesUseCase = getPageArticlesUseCase
        )

    }//end provideArticlesViewModel

}//end ViewModelsModule