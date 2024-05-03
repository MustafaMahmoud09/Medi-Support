package com.example.offline.booking.data.source.remote.data.dto.execution.topOfflineDoctors


import com.example.offline.booking.data.source.remote.data.dto.execution.OfflineDoctorDto
import com.example.offline.booking.domain.dto.declarations.topOfflineDoctors.ITopOfflineDoctorsResponseDto
import com.google.gson.annotations.SerializedName

data class TopOfflineDoctorsResponseDto(
    @SerializedName("data")
    override val `data`: List<OfflineDoctorDto?>?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
): ITopOfflineDoctorsResponseDto