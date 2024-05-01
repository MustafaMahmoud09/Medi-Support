package com.example.article.data.repository.execution

import com.example.article.data.repository.execution.cacheHelperDeclarations.IServerArticleRepositoryHelper
import com.example.article.domain.entity.declarations.IArticleEntity
import com.example.artocle.domain.repository.declarations.IArticleRepository
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import kotlinx.coroutines.flow.Flow

class ArticleRepositoryImpl(
    private val serverArticleRepositoryHelper: IServerArticleRepositoryHelper,
    private val localDatabase: MediSupportDatabase,
) : IArticleRepository {

    //function for provide page articles
    override suspend fun getPageArticles(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IArticleEntity>> {

        //get article from server here
        val lastPage = serverArticleRepositoryHelper.getPageArticlesFromServer(
            page = page,
            pageSize = pageSize
        )

        return UnEffectResponse(
            lastPageNumber = lastPage,
            body = localDatabase.articleDao().selectPageArticle(
                page = page,
                pageSize = pageSize
            )
        )

    }//end getAllArticles


    //function for get article by id from server
    override suspend fun getArticleById(
        articleId: Long
    ): Flow<List<IArticleEntity>> {

        //get article from server by id
        serverArticleRepositoryHelper.getArticleByIdFromServer(
            articleId = articleId
        )

        //return flow of article by id from local database
        return localDatabase.articleDao().selectArticleById(
            id = articleId
        )

    }//end getArticleById

}//end ArticleRepositoryImpl