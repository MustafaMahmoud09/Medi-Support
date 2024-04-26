package com.example.blood.pressure.data.source.remote.data.dto.execution.adviceMeasurement

import com.example.blood.pressure.domain.dto.declarations.latestMeasurement.IAdviceBloodPressureDto
import com.google.gson.annotations.SerializedName

data class AdviceBloodPressureDto(
    @SerializedName("attributes")
    override val attributes: AdviceBloodPressureAttributes?,
    @SerializedName("id")
    override val id: Long?
) : IAdviceBloodPressureDto