package com.example.blood.sugar.data.source.remote.data.dto.execution.lastRecords


import com.example.blood.sugar.data.source.remote.data.dto.execution.BloodSugarDto
import com.google.gson.annotations.SerializedName

data class LatestBloodSugarResponseDto(
    @SerializedName("data")
    val `data`: List<BloodSugarDto?>?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)