package com.example.article.data.source.remote.dto.execution.articleById

import com.example.article.data.source.remote.dto.execution.ArticleDataDto
import com.example.article.domain.dto.declarations.articleById.IArticleResponseDto
import com.google.gson.annotations.SerializedName

data class ArticleResponseDto(
    @SerializedName("data")
    override val `data`: ArticleDataDto?,
    @SerializedName("message")
    override val message: Any?
) : IArticleResponseDto