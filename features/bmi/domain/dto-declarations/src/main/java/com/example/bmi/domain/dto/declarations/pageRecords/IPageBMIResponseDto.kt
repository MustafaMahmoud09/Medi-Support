package com.example.bmi.data.source.remote.data.dto.execution.pageRecords


import com.google.gson.annotations.SerializedName

data class PageBMIResponseDto(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)