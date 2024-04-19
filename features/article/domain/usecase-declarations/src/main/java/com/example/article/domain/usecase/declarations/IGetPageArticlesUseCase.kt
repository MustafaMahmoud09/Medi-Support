package com.example.article.domain.usecase.declarations

import com.example.article.domain.model.TitleArticleModel

interface IGetPageArticlesUseCase {

    suspend operator fun invoke(
        page: Int,
        pageSize: Int
    ): List<TitleArticleModel>

}//end IGetPageArticlesUseCase