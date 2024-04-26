package com.example.blood.pressure.data.source.remote.data.dto.execution

import com.example.blood.pressure.domain.dto.declarations.IBloodPressureDto
import com.google.gson.annotations.SerializedName

data class BloodPressureDto(
    @SerializedName("attributes")
    override val attributes: BloodPressureAttributes?,
    @SerializedName("id")
    override val id: Long?
) : IBloodPressureDto