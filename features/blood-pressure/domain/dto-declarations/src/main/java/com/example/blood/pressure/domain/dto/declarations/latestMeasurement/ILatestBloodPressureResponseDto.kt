package com.example.blood.pressure.data.source.remote.data.dto.execution.latestMeasurement


import com.google.gson.annotations.SerializedName

data class LatestBloodPressureResponseDto(
    @SerializedName("data")
    val latestBloodPressureDto: LatestBloodPressureDto?
)