package com.example.article.data.repository.execution.cacheHelperDeclarations

import com.example.article.domain.dto.declarations.articleById.IArticleResponseDto
import com.example.article.domain.dto.declarations.articles.IArticlesResponseDto

interface ICacheArticleRepositoryHelper {

    suspend fun cachePageArticles(
        articles: IArticlesResponseDto?,
        pageSize: Int
    ): Int


    suspend fun cacheSingleArticle(article: IArticleResponseDto)


    suspend fun getLocalPageCount(
        pageSize: Int
    ): Int

}//end ICacheArticleRepositoryHelper