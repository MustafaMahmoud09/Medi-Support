package com.damanhour.Graduation.medisupport.di.article

import com.example.article.domain.mapper.declarations.child.IArticleDtoToArticleEntityMapper
import com.example.article.domain.mapper.declarations.child.IArticleEntityToArticleTitleModelMapper
import com.example.article.domain.mapper.declarations.child.IArticleEntityToBodyArticleModelMapper
import com.example.article.mapper.execution.ArticleDtoToArticleEntityMapper
import com.example.article.mapper.execution.ArticleEntityToArticleTitleModelMapper
import com.example.article.mapper.execution.ArticleEntityToBodyArticleModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {


    @Provides
    @Singleton
    fun provideArticleEntityToArticleTitleModelMapper(
        @Named("domain") imageUrl: String
    ): IArticleEntityToArticleTitleModelMapper {

        return ArticleEntityToArticleTitleModelMapper(
            imageUrl = imageUrl
        )

    }//end provideArticleEntityToArticleTitleModelMapper


    @Provides
    @Singleton
    fun provideArticleDtoToArticleEntityMapper()
            : IArticleDtoToArticleEntityMapper = ArticleDtoToArticleEntityMapper()


    @Provides
    @Singleton
    fun provideArticleEntityToBodyArticleModelMapper(
        @Named("domain") imageUrl: String
    ): IArticleEntityToBodyArticleModelMapper {

        return ArticleEntityToBodyArticleModelMapper(
            imageUrl = imageUrl
        )

    }//end provideArticleEntityToBodyArticleModelMapper

}//end MappersModule