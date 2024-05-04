package com.example.online.booking.domain.dto.declarations.topOnlineDoctors


import com.google.gson.annotations.SerializedName

data class TopOnlineDoctorResponseDto(
    @SerializedName("data")
    val `data`: List<Data?>?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)