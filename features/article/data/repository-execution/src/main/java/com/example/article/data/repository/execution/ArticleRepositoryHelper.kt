package com.example.article.data.repository.execution

import android.util.Log
import com.example.article.data.source.local.entity.execution.entities.article.ArticleEntity
import com.example.article.domain.dto.declarations.IArticleDataDto
import com.example.article.domain.dto.declarations.articleById.IArticleResponseDto
import com.example.article.domain.dto.declarations.articles.IArticlesResponseDto
import com.example.article.domain.mapper.declarations.child.IArticleDtoToArticleEntityMapper
import com.example.database_creator.MediSupportDatabase

class ArticleRepositoryHelper(
    private val localDatabase: MediSupportDatabase,
    private val articleDtoToArticleEntityMapper: IArticleDtoToArticleEntityMapper
) {

    //function for cache articles in local database
    suspend fun cacheArticles(articles: IArticlesResponseDto?): Int {

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
            else{

                //if current page is first page
                if (articles.meta?.currentPage == 1) {

                    //execute delete here
                    localDatabase.articleDao().deleteArticlesFromId(
                        startId = 0
                    )

                }//end if
                else{

                    val prevArticles = localDatabase.articleDao().selectPageArticle(
                        page = articles.meta?.currentPage!! - 1,
                        pageSize = 10
                    )

                    localDatabase.articleDao().deleteArticlesFromId(
                        startId = prevArticles[prevArticles.size - 1].id
                    )

                }//end else

            }//end else

        }//end try
        catch (ex: Exception) {
            ex.message?.let { Log.d("TAG", it) }
        }//end catch

        return articles?.meta?.lastPage!!

    }//end cacheArticles


    //function for cache single article in local database
    suspend fun cacheSingleArticle(article: IArticleResponseDto) {

        //map article from dto to entity here
        val articleEntity = articleDtoToArticleEntityMapper.objectConvertor(
            obj = article.data!!
        )

        //store article entity in local database here
        localDatabase.articleDao().insertArticles(
            articles = listOf(articleEntity) as List<ArticleEntity>
        )

    }//end cacheSingleArticle

}//end ArticleRepositoryHelper