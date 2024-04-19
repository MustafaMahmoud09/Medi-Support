package com.example.artocle.domain.repository.declarations

import com.example.article.domain.entity.declarations.IArticleEntity

interface IArticleRepository {

    suspend fun getPageArticles(
        page: Int,
        pageSize: Int
    ): List<IArticleEntity>

}//end IArticleRepository