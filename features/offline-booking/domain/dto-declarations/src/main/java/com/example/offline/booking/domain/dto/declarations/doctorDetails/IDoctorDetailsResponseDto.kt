package com.example.offline.booking.data.source.remote.data.dto.execution.doctorDetails


import com.google.gson.annotations.SerializedName

data class DoctorDetailsResponseDto(
    @SerializedName("data")
    val doctorDetailsDto: DoctorDetailsDto?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)