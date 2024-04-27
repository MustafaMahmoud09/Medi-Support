package com.example.blood.sugar.data.source.remote.data.dto.execution


import com.google.gson.annotations.SerializedName

data class BloodSugarDto(
    @SerializedName("advice")
    val advice: Advice?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("day-name")
    val dayName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("level")
    val level: String?,
    @SerializedName("user_id")
    val userId: Int?
)