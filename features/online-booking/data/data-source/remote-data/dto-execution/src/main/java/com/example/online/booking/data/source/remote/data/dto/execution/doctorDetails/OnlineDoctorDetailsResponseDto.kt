package com.example.online.booking.data.source.remote.data.dto.execution.doctorDetails

import com.example.online.booking.domain.dto.declarations.doctorDetails.IOnlineDoctorDetailsResponseDto
import com.google.gson.annotations.SerializedName

data class OnlineDoctorDetailsResponseDto(
    @SerializedName("data")
    override val `data`: OnlineDoctorDetailsDto?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
): IOnlineDoctorDetailsResponseDto