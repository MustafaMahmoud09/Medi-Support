package com.example.article.data.source.local.data.access.`object`

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.article.data.source.local.entity.execution.entities.article.ArticleEntity
import com.example.article.data.source.local.entity.execution.entities.article.ArticleInfo

@Dao
interface ArticleDao {

    //TODO:: FUNCTION FOR INSERT ARTICLES IN LOCAL DATABASE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)


    //TODO:: FUNCTION FROM DELETE ARTICLES FROM START ID TO END ID
    @Query(
        "DELETE FROM ${
            ArticleInfo.ARTICLE_TABLE_NAME
        } WHERE ${
            ArticleInfo.ID_COLUMN_NAME
        } > :startId AND ${
            ArticleInfo.ID_COLUMN_NAME
        } < :endId"
    )
    suspend fun deleteArticlesFromIdToId(startId: Long, endId: Long)


    //TODO:: FUNCTION FROM DELETE ARTICLES FROM START ID
    @Query(
        "DELETE FROM ${
            ArticleInfo.ARTICLE_TABLE_NAME
        } WHERE ${
            ArticleInfo.ID_COLUMN_NAME
        } > :startId"
    )
    suspend fun deleteArticlesFromId(startId: Long)


    //TODO:: FUNCTION FOR SELECT PAGE FROM ARTICLES TABLE
    @Transaction
    @Query(
        "SELECT * FROM ${
            ArticleInfo.ARTICLE_TABLE_NAME
        } LIMIT :pageSize" +
                " OFFSET ((:page - 1) * :pageSize)"
    )
    suspend fun selectPageArticle(pageSize: Int, page: Int): List<ArticleEntity>


    //TODO:: FUNCTION FOR PROVIDE ARTICLE RECORD COUNT
    @Query(
        "SELECT COUNT(*) FROM ${
            ArticleInfo.ARTICLE_TABLE_NAME
        }"
    )
    suspend fun selectArticleCount(): Long

}//end ArticleDao