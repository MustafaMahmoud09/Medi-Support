package com.example.article.data.source.remote.dto.execution.article


import com.example.article.domain.dto.declarations.article.IData
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("body")
    override val body: String?,
    @SerializedName("created_at")
    override val createdAt: String?,
    @SerializedName("doctor_id")
    override val doctorId: Int?,
    @SerializedName("id")
    override val id: Int?,
    @SerializedName("image")
    override val image: String?,
    @SerializedName("title")
    override val title: String?,
    @SerializedName("updated_at")
    override val updatedAt: String?
): IData