package com.example.artocle.domain.repository.declarations

import com.example.article.domain.entity.declarations.IArticleEntity
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import kotlinx.coroutines.flow.Flow

interface IArticleRepository {

    suspend fun getPageArticles(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IArticleEntity>>


    suspend fun getArticleById(articleId: Long): Flow<List<IArticleEntity>>

}//end IArticleRepository