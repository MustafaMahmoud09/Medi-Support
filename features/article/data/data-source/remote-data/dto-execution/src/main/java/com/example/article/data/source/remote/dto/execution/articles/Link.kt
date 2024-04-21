package com.example.article.data.source.remote.dto.execution.articles


import com.example.article.domain.dto.declarations.articles.ILink
import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("active")
    override val active: Boolean?,
    @SerializedName("label")
    override val label: String?,
    @SerializedName("url")
    override val url: String?
): ILink