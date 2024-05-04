package com.example.online.booking.data.source.remote.data.dto.execution.doctorDetails


import com.google.gson.annotations.SerializedName

data class OnlineDoctorDetailsResponseDto(
    @SerializedName("data")
    val `data`: OnlineDoctorDetailsDto?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)