package com.example.blood.sugar.data.source.remote.data.dto.execution.lastSeven


import com.google.gson.annotations.SerializedName

data class LatestBloodSugarResponseDto(
    @SerializedName("data")
    val `data`: List<Data?>?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)