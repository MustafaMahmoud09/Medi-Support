package com.example.blood.pressure.data.source.remote.data.dto.execution.latestMeasurement


import com.example.blood.pressure.data.source.remote.data.dto.execution.BloodPressureDto
import com.example.blood.pressure.domain.dto.declarations.latestMeasurement.ILatestBloodPressureResponseDto
import com.google.gson.annotations.SerializedName

data class LatestBloodPressureResponseDto(
    @SerializedName("data")
    override val `data`: List<BloodPressureDto?>?,
    @SerializedName("message")
    override val message: String?,
): ILatestBloodPressureResponseDto