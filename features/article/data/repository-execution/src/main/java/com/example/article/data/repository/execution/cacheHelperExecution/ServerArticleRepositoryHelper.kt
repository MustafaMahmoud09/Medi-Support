package com.example.article.data.repository.execution

import android.util.Log
import com.example.article.data.source.remote.dto.execution.articleById.ArticleResponseDto
import com.example.article.data.source.remote.dto.execution.articles.ArticlesResponseDto
import com.example.article.data.source.remote.requests.ArticleRequest
import com.example.article.domain.dto.declarations.articleById.IArticleResponseDto
import com.example.article.domain.dto.declarations.articles.IArticlesResponseDto
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ServerArticleRepositoryHelper(
    private val articleRequest: ArticleRequest,
    private val wrapper: ResponseWrapper,
    private val articleRepositoryHelper: ArticleRepositoryHelper,
    private val localDatabase: MediSupportDatabase,
) {

    //function for get article by id from server
    suspend fun getArticleByIdFromServer(
        articleId: Long
    ) {

        CoroutineScope(Dispatchers.IO).launch {

            while (true) {

                var breakCondition = false

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
                                    if (status.toData()?.body?.message.toString() != "null") {

                                        localDatabase.articleDao().deleteById(
                                            id = articleId
                                        )

                                    }//end if
                                }//end else if
                                breakCondition = true
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

                if (breakCondition) {
                    return@launch
                }//end if

            }//end while

        }//end CoroutineScope

    }//end getArticleByIdFromServer


    //fun for get page contain on article from server
    //after that cache data in local database
    suspend fun getPageArticlesFromServer(
        page: Int,
        pageSize: Int
    ): Int {

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
                            lastPage = articleRepositoryHelper.cachePageArticles(
                                status.toData()!!.body,
                                pageSize = pageSize
                            )
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

            lastPage = articleRepositoryHelper.getLocalPageCount(
                pageSize = pageSize
            )

        }//end if

        return lastPage

    }//end getPageArticlesFromServer

}//end ServerArticleRepositoryHelper