package com.example.offline.booking.data.source.remote.data.dto.execution.doctorDetails

import com.example.offline.booking.domain.dto.declarations.doctorDetails.IDoctorDetailsResponseDto
import com.google.gson.annotations.SerializedName

data class DoctorDetailsResponseDto(
    @SerializedName("data")
    override val doctorDetailsDto: DoctorDetailsDto?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
): IDoctorDetailsResponseDto