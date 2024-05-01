package com.damanhour.Graduation.medisupport.di.article

import com.example.article.data.repository.execution.cacheHelperExecution.CacheArticleRepositoryHelper
import com.example.article.data.repository.execution.ArticleRepositoryImpl
import com.example.article.data.repository.execution.cacheHelperDeclarations.ICacheArticleRepositoryHelper
import com.example.article.data.repository.execution.cacheHelperDeclarations.IServerArticleRepositoryHelper
import com.example.article.data.repository.execution.cacheHelperExecution.ServerArticleRepositoryHelper
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
        localDatabase: MediSupportDatabase,
        serverArticleRepositoryHelper: IServerArticleRepositoryHelper
    ): IArticleRepository {

        return ArticleRepositoryImpl(
            localDatabase = localDatabase,
            serverArticleRepositoryHelper = serverArticleRepositoryHelper
        )

    }//end provideArticleRepository


    @Provides
    @Singleton
    fun provideServerArticleRepositoryHelper(
        articleRequest: ArticleRequest,
        wrapper: ResponseWrapper,
        articleRepositoryHelper: ICacheArticleRepositoryHelper,
        localDatabase: MediSupportDatabase,
    ): IServerArticleRepositoryHelper {

        return ServerArticleRepositoryHelper(
            articleRequest = articleRequest,
            wrapper = wrapper,
            articleRepositoryHelper = articleRepositoryHelper,
            localDatabase = localDatabase
        )

    }//end provideServerArticleRepositoryHelper

    @Provides
    @Singleton
    fun provideArticleRepositoryHelper(
        localDatabase: MediSupportDatabase,
        articleDtoToArticleEntityMapper: IArticleDtoToArticleEntityMapper
    ): ICacheArticleRepositoryHelper {

        return CacheArticleRepositoryHelper(
            localDatabase = localDatabase,
            articleDtoToArticleEntityMapper = articleDtoToArticleEntityMapper
        )

    }//end provideArticleRepositoryHelper

}//end RepositoriesModule