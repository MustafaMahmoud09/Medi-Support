package com.example.bmi.data.source.remote.data.dto.execution


import com.example.bmi.domain.dto.declarations.IBMIDto
import com.google.gson.annotations.SerializedName

data class BMIDto(
    @SerializedName("advice")
    override val advice: String?,
    @SerializedName("advice_id")
    override val adviceId: Int?,
    @SerializedName("created_at")
    override val createdAt: String?,
    @SerializedName("day-name")
    override val dayName: String?,
    @SerializedName("id")
    override val id: Long?,
    @SerializedName("key")
    override val key: String?,
    @SerializedName("result")
    override val result: Double?,
    @SerializedName("user_id")
    override val userId: Long?
): IBMIDto