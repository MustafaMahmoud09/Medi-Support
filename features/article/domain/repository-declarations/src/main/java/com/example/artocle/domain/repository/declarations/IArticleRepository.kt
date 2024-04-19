package com.example.artocle.domain.repository.declarations

import com.example.article.domain.entity.declarations.IArticleEntity
import com.example.libraries.core.remote.data.response.status.UnEffectResponse

interface IArticleRepository {

    suspend fun getPageArticles(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IArticleEntity>>

}//end IArticleRepository