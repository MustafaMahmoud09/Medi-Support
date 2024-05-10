package com.example.article.data.repository.execution.cacheHelperDeclarations

interface IServerArticleRepositoryHelper {

    suspend fun getArticleByIdFromServer(
        articleId: Long
    )


    suspend fun getPageArticlesFromServer(
        page: Int,
        pageSize: Int
    ): Int

}//end IServerArticleRepositoryHelper