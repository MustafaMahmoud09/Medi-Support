package com.example.heart.rate.data.source.dto.execution


import com.example.heart.rate.domain.dto.declarations.IAdvice
import com.google.gson.annotations.SerializedName

data class Advice(
    @SerializedName("advice")
    override val advice: String?,
    @SerializedName("id")
    override val id: Int?,
    @SerializedName("key")
    override val key: String?
): IAdvice