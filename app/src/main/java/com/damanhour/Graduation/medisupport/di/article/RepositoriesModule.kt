package com.damanhour.Graduation.medisupport.di.article

import com.example.article.data.repository.execution.ArticleRepositoryHelper
import com.example.article.data.repository.execution.ArticleRepositoryImpl
import com.example.article.data.source.remote.requests.ArticleRequest
import com.example.article.domain.mapper.declarations.child.IArticleDtoToArticleEntityMapper
import com.example.artocle.domain.repository.declarations.IArticleRepository
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideArticleRepository(
        articleRequest: ArticleRequest,
        wrapper: ResponseWrapper,
        localDatabase: MediSupportDatabase,
        articleRepositoryHelper: ArticleRepositoryHelper
    ): IArticleRepository {

        return ArticleRepositoryImpl(
            articleRequest = articleRequest,
            wrapper = wrapper,
            localDatabase = localDatabase,
            articleRepositoryHelper = articleRepositoryHelper
        )

    }//end provideArticleRepository


    @Provides
    @Singleton
    fun provideArticleRepositoryHelper(
        localDatabase: MediSupportDatabase,
        articleDtoToArticleEntityMapper: IArticleDtoToArticleEntityMapper
    ): ArticleRepositoryHelper {

        return ArticleRepositoryHelper(
            localDatabase = localDatabase,
            articleDtoToArticleEntityMapper = articleDtoToArticleEntityMapper
        )

    }//end provideArticleRepositoryHelper

}//end RepositoriesModule