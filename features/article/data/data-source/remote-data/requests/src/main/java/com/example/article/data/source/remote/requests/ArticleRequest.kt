package com.example.article.data.source.remote.requests

import com.example.article.data.source.remote.dto.execution.article.ArticleResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleRequest {

    /**
     * function for make get articles request on server
     **/
    @GET("articles")
    suspend fun getArticles(
        @Query("page") page: Int
    ): Response<ArticleResponseDto>

}//end ArticleRequest