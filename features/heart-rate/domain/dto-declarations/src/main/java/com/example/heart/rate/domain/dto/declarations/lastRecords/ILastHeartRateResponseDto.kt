package com.example.heart.rate.data.source.dto.execution.lastRecords


import com.example.heart.rate.data.source.dto.execution.HeartRateDto
import com.google.gson.annotations.SerializedName

data class LastHeartRateResponseDto(
    @SerializedName("data")
    val `data`: List<HeartRateDto?>?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)