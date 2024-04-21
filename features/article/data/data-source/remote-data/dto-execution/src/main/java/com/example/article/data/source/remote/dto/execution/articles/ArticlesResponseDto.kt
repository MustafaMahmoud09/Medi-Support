package com.example.article.data.source.remote.dto.execution.articles

import com.example.article.data.source.remote.dto.execution.ArticleDataDto
import com.example.article.domain.dto.declarations.articles.IArticlesResponseDto
import com.google.gson.annotations.SerializedName

data class ArticlesResponseDto(
    @SerializedName("data")
    override val `data`: List<ArticleDataDto?>?,
    @SerializedName("links")
    override val links: Links?,
    @SerializedName("meta")
    override val meta: Meta?
) : IArticlesResponseDto