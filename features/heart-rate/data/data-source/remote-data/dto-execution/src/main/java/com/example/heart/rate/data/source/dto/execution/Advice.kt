package com.example.heart.rate.data.source.dto.execution.pageRecords


import com.google.gson.annotations.SerializedName

data class Advice(
    @SerializedName("advice")
    val advice: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("key")
    val key: String?
)