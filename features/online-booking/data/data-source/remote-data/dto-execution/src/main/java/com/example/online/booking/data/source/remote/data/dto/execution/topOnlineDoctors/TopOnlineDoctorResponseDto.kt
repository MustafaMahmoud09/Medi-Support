package com.example.online.booking.data.source.remote.data.dto.execution.topOnlineDoctors

import com.example.online.booking.data.source.remote.data.dto.execution.OnlineDoctorDto
import com.example.online.booking.domain.dto.declarations.topOnlineDoctors.ITopOnlineDoctorResponseDto
import com.google.gson.annotations.SerializedName

data class TopOnlineDoctorResponseDto(
    @SerializedName("data")
    override val `data`: List<OnlineDoctorDto?>?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
): ITopOnlineDoctorResponseDto