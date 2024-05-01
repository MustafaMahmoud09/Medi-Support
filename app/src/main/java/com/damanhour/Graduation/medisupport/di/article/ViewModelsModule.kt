package com.damanhour.Graduation.medisupport.di.article

import com.example.article.domain.usecase.declarations.IGetPageArticlesUseCase
import com.example.article.presentation.uiState.viewModel.ArticlesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelsModule {


    @Provides
    fun provideArticlesViewModel(
        getPageArticlesUseCase: IGetPageArticlesUseCase
    ): ArticlesViewModel {

        return ArticlesViewModel(
            getPageArticlesUseCase = getPageArticlesUseCase
        )

    }//end provideArticlesViewModel

//    @Provides
//    fun provideArticleViewModel(
//        getArticleByIdUseCase: IGetArticleByIdUseCase
//    ): ArticleViewModel {
//
//        return ArticleViewModel(
//            getArticleByIdUseCase = getArticleByIdUseCase
//        )
//
//    }//end provideArticleViewModel

}//end ViewModelsModule