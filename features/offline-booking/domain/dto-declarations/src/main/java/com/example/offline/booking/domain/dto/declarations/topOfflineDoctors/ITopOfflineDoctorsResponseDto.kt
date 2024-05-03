package com.example.offline.booking.data.source.remote.data.dto.execution.topOfflineDoctors


import com.example.offline.booking.data.source.remote.data.dto.execution.OfflineDoctorDto
import com.google.gson.annotations.SerializedName

data class TopOfflineDoctorsResponseDto(
    @SerializedName("data")
    val `data`: List<OfflineDoctorDto?>?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)