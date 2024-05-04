package com.example.online.booking.data.source.remote.data.dto.execution.pageOnlineDoctor


import com.google.gson.annotations.SerializedName

data class PageOnlineDoctorResponseDto(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)