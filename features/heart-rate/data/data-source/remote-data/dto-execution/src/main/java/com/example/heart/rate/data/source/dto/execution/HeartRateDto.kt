package com.example.heart.rate.data.source.dto.execution

import com.example.heart.rate.domain.dto.declarations.IHeartRateDto
import com.google.gson.annotations.SerializedName

data class HeartRateDto(
    @SerializedName("advice")
    override val advice: Advice?,
    @SerializedName("created_at")
    override val createdAt: String?,
    @SerializedName("day-name")
    override val dayName: String?,
    @SerializedName("heart_rate")
    override val heartRate: Long?,
    @SerializedName("id")
    override val id: Long?,
    @SerializedName("user_id")
    override val userId: Long?
): IHeartRateDto