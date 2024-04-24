package com.example.blood.pressure.data.source.remote.data.dto.execution.descMeasurement


import com.example.blood.pressure.domain.dto.declarations.descMeasurement.IDescBloodPressureResponseDto
import com.google.gson.annotations.SerializedName

data class DescBloodPressureResponseDto(
    @SerializedName("data")
    override val `data`: Map<String, Long>?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
) : IDescBloodPressureResponseDto