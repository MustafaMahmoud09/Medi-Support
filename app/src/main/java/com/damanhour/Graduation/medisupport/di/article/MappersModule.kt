package com.damanhour.Graduation.medisupport.di.article

import com.example.article.domain.mapper.declarations.child.IArticleDtoToArticleEntityMapper
import com.example.article.domain.mapper.declarations.child.IArticleEntityToArticleTitleModelMapper
import com.example.article.mapper.execution.ArticleDtoToArticleEntityMapper
import com.example.article.mapper.execution.ArticleEntityToArticleTitleModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {


    @Provides
    @Singleton
    fun provideArticleEntityToArticleTitleModelMapper()
            : IArticleEntityToArticleTitleModelMapper = ArticleEntityToArticleTitleModelMapper()


    @Provides
    @Singleton
    fun provideArticleDtoToArticleEntityMapper()
            : IArticleDtoToArticleEntityMapper = ArticleDtoToArticleEntityMapper()

}//end MappersModule