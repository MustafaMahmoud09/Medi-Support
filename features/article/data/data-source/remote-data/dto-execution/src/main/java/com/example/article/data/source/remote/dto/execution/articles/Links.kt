package com.example.article.data.source.remote.dto.execution.article


import com.example.article.domain.dto.declarations.article.ILinks
import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("first")
    override val first: String?,
    @SerializedName("last")
    override val last: String?,
    @SerializedName("next")
    override val next: String?,
    @SerializedName("prev")
    override val prev: String?
): ILinks