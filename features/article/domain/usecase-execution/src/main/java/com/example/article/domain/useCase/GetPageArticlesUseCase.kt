package com.example.article.domain.useCase

import com.example.article.domain.mapper.declarations.child.IArticleEntityToArticleTitleModelMapper
import com.example.article.domain.model.TitleArticleModel
import com.example.article.domain.usecase.declarations.IGetPageArticlesUseCase
import com.example.artocle.domain.repository.declarations.IArticleRepository

class GetPageArticlesUseCase(
    private val articleRepository: IArticleRepository,
    private val articleEntityToArticleTitleModelMapper: IArticleEntityToArticleTitleModelMapper
) : IGetPageArticlesUseCase {

    //function for get page from articles
    override suspend fun invoke(
        page: Int,
        pageSize: Int
    ): List<TitleArticleModel> {

        //get entity articles here
        val articleEntities = articleRepository.getPageArticles(
            page = page,
            pageSize = pageSize
        )

        //map articles from entity to model here
        return articleEntityToArticleTitleModelMapper.listConvertor(
            list = articleEntities
        )

    }//end invoke

}//end GetPageArticlesUseCase