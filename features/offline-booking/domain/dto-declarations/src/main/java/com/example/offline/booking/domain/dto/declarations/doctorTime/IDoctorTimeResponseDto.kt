package com.example.offline.booking.data.source.remote.data.dto.execution.doctorTime


import com.google.gson.annotations.SerializedName

data class DoctorTimeResponseDto(
    @SerializedName("data")
    val `data`: List<Time?>?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)