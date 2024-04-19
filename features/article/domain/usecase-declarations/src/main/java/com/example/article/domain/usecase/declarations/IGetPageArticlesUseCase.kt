package com.example.article.domain.usecase.declarations

import com.example.article.domain.model.TitleArticleModel
import com.example.libraries.core.remote.data.response.status.UnEffectResponse

interface IGetPageArticlesUseCase {

    suspend operator fun invoke(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<TitleArticleModel>>

}//end IGetPageArticlesUseCase