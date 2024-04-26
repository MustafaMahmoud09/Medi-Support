package com.example.blood.pressure.data.source.remote.data.dto.execution.adviceMeasurement


import com.example.blood.pressure.domain.dto.declarations.latestMeasurement.IAdviceBloodPressureAttributes
import com.google.gson.annotations.SerializedName

data class AdviceBloodPressureAttributes(
    @SerializedName("created_at")
    override val createdAt: String?,
    @SerializedName("day-name")
    override val dayName: String?,
    @SerializedName("diastolic")
    override val diastolic: Int?,
    @SerializedName("pressure_advice_advice")
    override val pressureAdviceAdvice: String?,
    @SerializedName("pressure_advice_id")
    override val pressureAdviceId: String?,
    @SerializedName("pressure_advice_key")
    override val pressureAdviceKey: String?,
    @SerializedName("systolic")
    override val systolic: Int?,
    @SerializedName("user_id")
    override val userId: Long?
): IAdviceBloodPressureAttributes