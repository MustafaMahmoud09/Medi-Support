package com.example.article.data.repository.execution.cacheHelperExecution

import com.example.article.data.repository.execution.cacheHelperDeclarations.ICacheArticleRepositoryHelper
import com.example.article.data.source.local.entity.execution.entities.article.ArticleEntity
import com.example.article.domain.dto.declarations.IArticleDataDto
import com.example.article.domain.dto.declarations.articleById.IArticleResponseDto
import com.example.article.domain.dto.declarations.articles.IArticlesResponseDto
import com.example.article.domain.mapper.declarations.child.IArticleDtoToArticleEntityMapper
import com.example.database_creator.MediSupportDatabase

class CacheArticleRepositoryHelper(
    private val localDatabase: MediSupportDatabase,
    private val articleDtoToArticleEntityMapper: IArticleDtoToArticleEntityMapper
): ICacheArticleRepositoryHelper {

    //function for cache articles in local database
    override suspend fun cachePageArticles(
        articles: IArticlesResponseDto?,
        pageSize: Int
    ): Int {

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
                        pageSize = pageSize
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
            else {

                //if current page is first page
                if (articles.meta?.currentPage == 1) {

                    //execute delete here
                    localDatabase.articleDao().deleteArticlesFromId(
                        startId = 0
                    )

                }//end if
                else {

                    val prevArticles = localDatabase.articleDao().selectPageArticle(
                        page = articles.meta?.currentPage!! - 1,
                        pageSize = pageSize
                    )

                    localDatabase.articleDao().deleteArticlesFromId(
                        startId = prevArticles[prevArticles.size - 1].id
                    )

                }//end else

            }//end else

            return articles.meta?.lastPage!!
        }//end try
        catch (ex: Exception) {
            return 0
        }//end catch

    }//end cacheArticles


    //function for cache single article in local database
    override suspend fun cacheSingleArticle(article: IArticleResponseDto) {

        //map article from dto to entity here
        val articleEntity = articleDtoToArticleEntityMapper.objectConvertor(
            obj = article.data!!
        )

        //store article entity in local database here
        localDatabase.articleDao().insertArticles(
            articles = listOf(articleEntity) as List<ArticleEntity>
        )

    }//end cacheSingleArticle


    override suspend fun getLocalPageCount(
        pageSize: Int
    ): Int {

        //get article size
        val articleSize = localDatabase.articleDao().selectArticleCount()

        return if ((articleSize.toFloat() / pageSize.toFloat()) - (articleSize / pageSize) != 0f) {
            (articleSize / pageSize) + 1
        }//end if
        else {
            (articleSize / pageSize)
        }.toInt()//end else

    }//end getLocalPageCount

}//end ArticleRepositoryHelper