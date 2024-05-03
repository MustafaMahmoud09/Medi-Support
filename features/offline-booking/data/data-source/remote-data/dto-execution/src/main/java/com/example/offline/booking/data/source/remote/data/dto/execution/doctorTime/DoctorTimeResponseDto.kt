package com.example.offline.booking.data.source.remote.data.dto.execution.doctorTime

import com.example.offline.booking.domain.dto.declarations.doctorTime.IDoctorTimeResponseDto
import com.google.gson.annotations.SerializedName

data class DoctorTimeResponseDto(
    @SerializedName("data")
    override val `data`: List<Time?>?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
): IDoctorTimeResponseDto