package com.example.heart.rate.data.source.dto.execution.pageRecords


import com.google.gson.annotations.SerializedName

data class PageHeartRateResponseDto(
    @SerializedName("data")
    override val `data`: Data?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
): IPageHeartRateResponseDto