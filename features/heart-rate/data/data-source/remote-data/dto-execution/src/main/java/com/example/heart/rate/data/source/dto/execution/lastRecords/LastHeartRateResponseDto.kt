package com.example.heart.rate.data.source.dto.execution.lastRecords


import com.example.heart.rate.data.source.dto.execution.HeartRateDto
import com.google.gson.annotations.SerializedName

data class LastHeartRateResponseDto(
    @SerializedName("data")
    override val `data`: List<HeartRateDto?>?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
) : ILastHeartRateResponseDto