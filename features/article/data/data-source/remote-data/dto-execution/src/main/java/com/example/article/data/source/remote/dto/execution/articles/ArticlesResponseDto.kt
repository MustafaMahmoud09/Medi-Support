package com.example.article.data.source.remote.dto.execution.article

import com.example.article.domain.dto.declarations.article.IArticleResponseDto
import com.google.gson.annotations.SerializedName

data class ArticleResponseDto(
    @SerializedName("data")
    override val `data`: List<ArticleDataDto?>?,
    @SerializedName("links")
    override val links: Links?,
    @SerializedName("meta")
    override val meta: Meta?
) : IArticleResponseDto