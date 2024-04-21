package com.example.article.domain.useCase

import com.example.article.domain.mapper.declarations.child.IArticleEntityToBodyArticleModelMapper
import com.example.article.domain.model.BodyArticleModel
import com.example.article.domain.usecase.declarations.IGetArticleByIdUseCase
import com.example.artocle.domain.repository.declarations.IArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GetArticleByIdUseCase(
    private val articleRepository: IArticleRepository,
    private val articleEntityToBodyArticleModelMapper: IArticleEntityToBodyArticleModelMapper
) : IGetArticleByIdUseCase {

    //function for provide article by id
    override suspend fun invoke(id: Long): Flow<List<BodyArticleModel>> {

        //return flow of body article model
        return flow {

            //make observe on article result
            //collect data and convert this data from entity to model and emit it
            articleRepository.getArticleById(
                articleId = id
            ).collect { article ->

                //map article from entity to body model here
                val articleModel = articleEntityToBodyArticleModelMapper.listConvertor(
                    list = article
                )

                //emit article model here
                emit(articleModel)
            }//end collectLatest
        }//end flow

    }//end invoke

}//end GetArticleByIdUseCase