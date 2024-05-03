package com.example.offline.booking.data.source.remote.data.dto.execution.allOfflineDoctors


import com.google.gson.annotations.SerializedName

data class AllOfflineDoctorsResponseDto(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)