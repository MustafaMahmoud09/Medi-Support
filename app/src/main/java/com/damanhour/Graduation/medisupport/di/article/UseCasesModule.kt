package com.damanhour.Graduation.medisupport.di.article

import com.example.article.domain.mapper.declarations.child.IArticleEntityToArticleTitleModelMapper
import com.example.article.domain.mapper.declarations.child.IArticleEntityToBodyArticleModelMapper
import com.example.article.domain.useCase.GetArticleByIdUseCase
import com.example.article.domain.useCase.GetPageArticlesUseCase
import com.example.article.domain.usecase.declarations.IGetArticleByIdUseCase
import com.example.article.domain.usecase.declarations.IGetPageArticlesUseCase
import com.example.artocle.domain.repository.declarations.IArticleRepository
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
    fun provideGetPageArticlesUseCase(
        articleRepository: IArticleRepository,
        articleEntityToArticleTitleModelMapper: IArticleEntityToArticleTitleModelMapper
    ): IGetPageArticlesUseCase {

        return GetPageArticlesUseCase(
            articleRepository = articleRepository,
            articleEntityToArticleTitleModelMapper = articleEntityToArticleTitleModelMapper
        )

    }//end provideGetPageArticlesUseCase


    @Provides
    @Singleton
    fun provideGetArticleByIdUseCase(
        articleRepository: IArticleRepository,
        articleEntityToBodyArticleModelMapper: IArticleEntityToBodyArticleModelMapper
    ): IGetArticleByIdUseCase {

        return GetArticleByIdUseCase(
            articleRepository = articleRepository,
            articleEntityToBodyArticleModelMapper = articleEntityToBodyArticleModelMapper
        )

    }//end IGetArticleByIdUseCase

}//end UseCasesModule