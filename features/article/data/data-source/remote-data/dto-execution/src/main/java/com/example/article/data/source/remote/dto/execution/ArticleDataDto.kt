package com.example.article.data.source.remote.dto.execution.articles


import com.example.article.domain.dto.declarations.article.IArticleDataDto
import com.google.gson.annotations.SerializedName

data class ArticleDataDto(
    @SerializedName("body")
    override val body: String?,
    @SerializedName("created_at")
    override val createdAt: String?,
    @SerializedName("doctor_id")
    override val doctorId: Long?,
    @SerializedName("id")
    override val id: Long?,
    @SerializedName("image")
    override val image: String?,
    @SerializedName("title")
    override val title: String?,
    @SerializedName("updated_at")
    override val updatedAt: String?
): IArticleDataDto