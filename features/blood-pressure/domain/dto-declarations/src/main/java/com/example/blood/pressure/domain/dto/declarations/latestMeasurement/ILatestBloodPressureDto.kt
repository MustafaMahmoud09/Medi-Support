package com.example.blood.pressure.data.source.remote.data.dto.execution.latestMeasurement


import com.google.gson.annotations.SerializedName

data class LatestBloodPressureDto(
    @SerializedName("attributes")
    val attributes: Attributes?,
    @SerializedName("id")
    val id: String?
)