package com.example.article.data.source.remote.requests

import com.example.article.data.source.remote.dto.execution.articleById.ArticleResponseDto
import com.example.article.data.source.remote.dto.execution.articles.ArticlesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticleRequest {

    /**
     * function for make get articles request on server
     **/
    @GET("articles")
    suspend fun getArticles(
        @Query("page") page: Int
    ): Response<ArticlesResponseDto>


    /**
     * function for make get single article by id request on server
     **/
    @GET("articles/{articleId}")
    suspend fun getArticleById(
        @Path("articleId") articleId: Long
    ): Response<ArticleResponseDto>

}//end ArticleRequest