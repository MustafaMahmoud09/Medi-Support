package com.example.blood.pressure.data.source.remote.data.dto.execution.latestMeasurement


import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("day-name")
    val dayName: String?,
    @SerializedName("diastolic")
    val diastolic: Int?,
    @SerializedName("pressure_advice_advice")
    val pressureAdviceAdvice: String?,
    @SerializedName("pressure_advice_id")
    val pressureAdviceId: String?,
    @SerializedName("pressure_advice_key")
    val pressureAdviceKey: String?,
    @SerializedName("systolic")
    val systolic: Int?,
    @SerializedName("user_id")
    val userId: String?
)