package com.example.article.domain.useCase

import com.example.article.domain.mapper.declarations.child.IArticleEntityToArticleTitleModelMapper
import com.example.article.domain.model.TitleArticleModel
import com.example.article.domain.usecase.declarations.IGetPageArticlesUseCase
import com.example.artocle.domain.repository.declarations.IArticleRepository
import com.example.libraries.core.remote.data.response.status.UnEffectResponse

class GetPageArticlesUseCase(
    private val articleRepository: IArticleRepository,
    private val articleEntityToArticleTitleModelMapper: IArticleEntityToArticleTitleModelMapper
) : IGetPageArticlesUseCase {

    //function for get page from articles
    override suspend fun invoke(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<TitleArticleModel>> {

        //get entity articles here
        val articleResponse = articleRepository.getPageArticles(
            page = page,
            pageSize = pageSize
        )

        //map articles from entity to model here
        val articleModel = articleEntityToArticleTitleModelMapper.listConvertor(
            list = articleResponse.body ?: emptyList()
        )

        return UnEffectResponse(
            lastPageNumber = articleResponse.lastPageNumber,
            body = articleModel
        )

    }//end invoke

}//end GetPageArticlesUseCase