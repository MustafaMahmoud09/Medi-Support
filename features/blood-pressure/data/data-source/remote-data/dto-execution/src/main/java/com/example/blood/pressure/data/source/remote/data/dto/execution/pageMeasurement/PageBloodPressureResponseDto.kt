package com.example.blood.pressure.data.source.remote.data.dto.execution.pageMeasurement


import com.example.blood.pressure.data.source.remote.data.dto.execution.BloodPressureDto
import com.example.blood.pressure.domain.dto.declarations.pageMeasurement.IPageBloodPressureResponseDto
import com.google.gson.annotations.SerializedName

data class PageBloodPressureResponseDto(
    @SerializedName("data")
    override val `data`: List<BloodPressureDto?>?,
    @SerializedName("links")
    override val links: Links?,
    @SerializedName("meta")
    override val meta: Meta?
): IPageBloodPressureResponseDto