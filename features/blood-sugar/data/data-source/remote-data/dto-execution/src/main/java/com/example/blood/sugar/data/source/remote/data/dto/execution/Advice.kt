package com.example.blood.sugar.data.source.remote.data.dto.execution


import com.example.blood.sugar.domain.dto.declarations.IAdvice
import com.google.gson.annotations.SerializedName

data class Advice(
    @SerializedName("advice")
    override val advice: String?,
    @SerializedName("id")
    override val id: Int?,
    @SerializedName("key")
    override val key: String?
): IAdvice