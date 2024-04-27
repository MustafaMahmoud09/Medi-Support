package com.example.blood.sugar.data.source.remote.data.dto.execution


import com.example.blood.sugar.domain.dto.declarations.IBloodSugarDto
import com.google.gson.annotations.SerializedName

data class BloodSugarDto(
    @SerializedName("advice")
    override val advice: Advice?,
    @SerializedName("created_at")
    override val createdAt: String?,
    @SerializedName("day-name")
    override val dayName: String?,
    @SerializedName("id")
    override val id: Long?,
    @SerializedName("level")
    override val level: Double?,
    @SerializedName("user_id")
    override val userId: Long?
): IBloodSugarDto