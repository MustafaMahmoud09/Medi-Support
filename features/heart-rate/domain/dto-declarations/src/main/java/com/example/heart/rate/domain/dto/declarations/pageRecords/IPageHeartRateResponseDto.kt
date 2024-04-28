package com.example.heart.rate.data.source.dto.execution.pageRecords


import com.google.gson.annotations.SerializedName

data class PageHeartRateResponseDto(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)