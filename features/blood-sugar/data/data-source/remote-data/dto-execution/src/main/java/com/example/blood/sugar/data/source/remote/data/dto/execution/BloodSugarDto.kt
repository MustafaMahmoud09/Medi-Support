package com.example.blood.sugar.data.source.remote.data.dto.execution.lastRecords


import com.google.gson.annotations.SerializedName

data class Data(
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