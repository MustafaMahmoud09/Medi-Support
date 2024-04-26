package com.example.blood.pressure.data.source.remote.data.dto.execution.adviceMeasurement

import com.example.blood.pressure.data.source.remote.data.dto.execution.BloodPressureDto
import com.example.blood.pressure.domain.dto.declarations.adviceMeasurement.IAdviceBloodPressureResponseDto
import com.google.gson.annotations.SerializedName

data class AdviceBloodPressureResponseDto(
    @SerializedName("data")
    override val data: BloodPressureDto?,
    @SerializedName("message")
    override val message: String?,
): IAdviceBloodPressureResponseDto