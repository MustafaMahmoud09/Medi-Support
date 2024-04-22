package com.example.article.data.repository.execution

import android.util.Log
import com.example.article.data.source.remote.dto.execution.articleById.ArticleResponseDto
import com.example.article.data.source.remote.dto.execution.articles.ArticlesResponseDto
import com.example.article.data.source.remote.requests.ArticleRequest
import com.example.article.domain.dto.declarations.articleById.IArticleResponseDto
import com.example.article.domain.dto.declarations.articles.IArticlesResponseDto
import com.example.article.domain.entity.declarations.IArticleEntity
import com.example.artocle.domain.repository.declarations.IArticleRepository
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ArticleRepositoryImpl(
    private val articleRequest: ArticleRequest,
    private val wrapper: ResponseWrapper,
    private val articleRepositoryHelper: ArticleRepositoryHelper,
    private val localDatabase: MediSupportDatabase,
) : IArticleRepository {

    //function for provide page articles
    override suspend fun getPageArticles(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IArticleEntity>> {

        var lastPage = 0

        try {
            //make request on server here
            //collect and handle response here
            wrapper.wrapper<IArticlesResponseDto, ArticlesResponseDto> {
                articleRequest.getArticles(
                    page = page
                )
            }.collectLatest { status ->

                when (status) {

                    is Status.Success -> {

                        //if status code equal 200
                        //process is success
                        if (status.toData()?.statusCode == 200) {
                            //make cache article in local database here
                            lastPage = articleRepositoryHelper.cacheArticles(status.toData()!!.body)
                        }//end if
                        return@collectLatest
                    }//end Success

                    is Status.Loading -> {

                    }//end Load

                    is Status.Error -> {
                        return@collectLatest
                    }//end Error
                }//end when

            }//end collectLatest

        }//end try
        catch (ex: Exception) {
            ex.message?.let { Log.d("ERROR", it) }
        }


        //if last page equal 0 get last page number in local database
        if (lastPage == 0) {
            //get article size
            val articleSize = localDatabase.articleDao().selectArticleCount()

            lastPage =
                if ((articleSize.toFloat() / pageSize.toFloat()) - (articleSize / pageSize) != 0f) {
                    (articleSize / pageSize) + 1
                }//end if
                else {
                    (articleSize / pageSize)
                }.toInt()//end else
        }//end if

        return UnEffectResponse(
            lastPageNumber = lastPage,
            body = localDatabase.articleDao().selectPageArticle(
                page = page,
                pageSize = pageSize
            )
        )

    }//end getAllArticles


    //function for get article by id from server
    override suspend fun getArticleById(articleId: Long): Flow<List<IArticleEntity>> {

        CoroutineScope(Dispatchers.IO).launch {

            try {

                //make request on serve for get article by id here
                //collect request status here
                wrapper.wrapper<IArticleResponseDto, ArticleResponseDto> {
                    articleRequest.getArticleById(
                        articleId = articleId
                    )
                }.collectLatest { status ->

                    when (status) {

                        is Status.Success -> {
                            //if status code equal 200
                            //process is success
                            if (status.toData()?.statusCode == 200) {

                                //make cache article in local database here
                                articleRepositoryHelper.cacheSingleArticle(
                                    article = status.toData()?.body!!
                                )
                            }//end if
                            else if (status.toData()?.statusCode == 404) {
                                if (
                                    status.toData()?.body?.message.toString().isNotEmpty() &&
                                    status.toData()?.body?.message.toString() != "null"
                                ) {

                                    localDatabase.articleDao().deleteById(
                                        id = articleId
                                    )

                                }//end if
                            }//end else if
                            return@collectLatest
                        }//end success case

                        is Status.Loading -> {

                        }//end loading case

                        is Status.Error -> {
                            return@collectLatest
                        }//end error case

                    }//end when

                }//end collectLatest

            } catch (ex: Exception) {
                ex.message?.let { Log.d("ERROR", it) }
            }

        }//end CoroutineScope

        //return flow of article by id from local database
        return localDatabase.articleDao().selectArticleById(
            id = articleId
        )

    }//end getArticleById

}//end ArticleRepositoryImpl