package com.example.article.data.source.remote.dto.execution.article

import com.example.article.domain.dto.declarations.article.IArticleDto
import com.google.gson.annotations.SerializedName

data class ArticleDto(
    @SerializedName("data")
    override val `data`: List<Data?>?,
    @SerializedName("links")
    override val links: Links?,
    @SerializedName("meta")
    override val meta: Meta?
) : IArticleDto