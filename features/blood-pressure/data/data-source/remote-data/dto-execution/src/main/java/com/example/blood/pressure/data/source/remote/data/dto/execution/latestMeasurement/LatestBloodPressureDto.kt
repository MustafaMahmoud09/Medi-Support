package com.example.blood.pressure.data.source.remote.data.dto.execution.latestMeasurement

import com.example.blood.pressure.domain.dto.declarations.latestMeasurement.ILatestBloodPressureDto
import com.google.gson.annotations.SerializedName

data class LatestBloodPressureDto(
    @SerializedName("attributes")
    override val attributes: LatestBloodPressureAttributes?,
    @SerializedName("id")
    override val id: Long?
) : ILatestBloodPressureDto