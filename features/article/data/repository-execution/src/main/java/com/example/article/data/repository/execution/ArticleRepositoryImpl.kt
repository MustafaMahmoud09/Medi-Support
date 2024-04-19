package com.example.article.data.repository.execution

import android.util.Log
import com.example.article.data.source.local.entity.execution.entities.article.ArticleEntity
import com.example.article.data.source.remote.dto.execution.article.ArticleResponseDto
import com.example.article.data.source.remote.requests.ArticleRequest
import com.example.article.domain.dto.declarations.article.IArticleDataDto
import com.example.article.domain.dto.declarations.article.IArticleResponseDto
import com.example.article.domain.entity.declarations.IArticleEntity
import com.example.article.domain.mapper.declarations.child.IArticleDtoToArticleEntityMapper
import com.example.artocle.domain.repository.declarations.IArticleRepository
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import kotlinx.coroutines.flow.collectLatest

class ArticleRepositoryImpl(
    private val articleRequest: ArticleRequest,
    private val wrapper: ResponseWrapper,
    private val localDatabase: MediSupportDatabase,
    private val articleDtoToArticleEntityMapper: IArticleDtoToArticleEntityMapper
) : IArticleRepository {

    //function for provide page articles
    override suspend fun getPageArticles(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IArticleEntity>> {

        var lastPage = 0

        //make request on server here
        //collect and handle response here
        wrapper.wrapper<IArticleResponseDto, ArticleResponseDto> {
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
                        lastPage = cacheArticles(status.toData()!!.body)
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

        //get article size
        val articleSize = localDatabase.articleDao().selectArticleCount()

        //if last page equal 0 get last page number in local database
        if (lastPage == 0) {
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

    //function for cache articles in local database
    private suspend fun cacheArticles(articles: IArticleResponseDto?): Int {

        try {

            if (articles?.data!!.isNotEmpty()) {

                //execute map data from dto to entity here
                val articleEntities = articleDtoToArticleEntityMapper.listConvertor(
                    list = articles.data as List<IArticleDataDto>
                )

                //store article entities in local database here
                localDatabase.articleDao().insertArticles(
                    articles = articleEntities as List<ArticleEntity>
                )

                //if current page is first page
                //delete first items if caching from 0 to first item exist in server now
                if (articles.meta?.currentPage == 1) {

                    //execute delete here
                    localDatabase.articleDao().deleteArticlesFromIdToId(
                        startId = 0L,
                        endId = articleEntities[0].id
                    )

                }//end if
                else {

                    val prevArticles = localDatabase.articleDao().selectPageArticle(
                        page = articles.meta?.currentPage!! - 1,
                        pageSize = 10
                    )

                    //execute delete here
                    localDatabase.articleDao().deleteArticlesFromIdToId(
                        startId = prevArticles[prevArticles.size - 1].id,
                        endId = articleEntities[0].id
                    )

                }//end else

                //execute delete between items here
                for (count in 0 until articleEntities.size - 1) {

                    //execute delete here
                    localDatabase.articleDao().deleteArticlesFromIdToId(
                        startId = articleEntities[count].id,
                        endId = articleEntities[count + 1].id
                    )

                }//end for

                //if current page is last page
                //delete items from last item in server to last item exist in local if exist
                if (articles.meta?.currentPage == articles.meta?.lastPage) {

                    //execute delete here
                    localDatabase.articleDao().deleteArticlesFromId(
                        startId = articleEntities[articleEntities.size - 1].id
                    )

                }//end if

            }//end if

        }//end try
        catch (ex: Exception) {
            ex.message?.let { Log.d("TAG", it) }
        }//end catch

        return articles?.meta?.lastPage!!

    }//end cacheArticles

}//end ArticleRepositoryImpl