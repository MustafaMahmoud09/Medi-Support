package com.example.blood.sugar.data.source.remote.data.dto.execution.status


import com.google.gson.annotations.SerializedName

data class BloodSugarStatusResponseDto(
    @SerializedName("data")
    val `data`: List<BloodSugarStatusDto?>?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)