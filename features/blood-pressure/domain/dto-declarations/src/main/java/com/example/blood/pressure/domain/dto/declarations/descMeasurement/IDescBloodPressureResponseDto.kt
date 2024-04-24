package com.example.blood.pressure.data.source.remote.data.dto.execution.descMeasurement


import com.google.gson.annotations.SerializedName

data class DescBloodPressureResponseDto(
    @SerializedName("data")
    val `data`: Map<String, Long>?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)