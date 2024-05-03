package com.example.offline.booking.data.source.remote.data.dto.execution.topOfflineDoctors


import com.google.gson.annotations.SerializedName

data class TopOfflineDoctorsResponsDto(
    @SerializedName("data")
    val `data`: List<Data?>?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)