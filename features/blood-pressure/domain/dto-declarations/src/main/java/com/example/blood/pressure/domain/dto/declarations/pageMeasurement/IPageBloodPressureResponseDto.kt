package com.example.blood.pressure.data.source.remote.data.dto.execution.pageMeasurement


import com.example.blood.pressure.data.source.remote.data.dto.execution.BloodPressureDto
import com.google.gson.annotations.SerializedName

data class PageBloodPressureResponseDto(
    @SerializedName("data")
    val `data`: List<BloodPressureDto?>?,
    @SerializedName("links")
    val links: Links?,
    @SerializedName("meta")
    val meta: Meta?
)