package com.example.article.data.source.local.entity.execution.entities.article

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.article.domain.entity.declarations.IArticleEntity
import java.sql.Date

@Entity(tableName = ArticleInfo.ARTICLE_TABLE_NAME)
data class ArticleEntity(
    @PrimaryKey
    @ColumnInfo(
        name = ArticleInfo.ID_COLUMN_NAME
    ) override val id: Long,
    @ColumnInfo(
        name = ArticleInfo.TITLE_COLUMN_NAME
    ) override val title: String?,
    @ColumnInfo(
        name = ArticleInfo.BODY_COLUMN_NAME
    ) override val body: String?,
    @ColumnInfo(
        name = ArticleInfo.IMAGE_COLUMN_NAME
    ) override val image: String?,
    @ColumnInfo(
        name = ArticleInfo.CREATE_AT_COLUMN_NAME
    ) override val createdAt: String,
) : IArticleEntity