package com.example.heart.rate.data.source.dto.execution.pageRecords


import com.google.gson.annotations.SerializedName

data class Record(
    @SerializedName("advice")
    val advice: Advice?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("day-name")
    val dayName: String?,
    @SerializedName("heart_rate")
    val heartRate: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("user_id")
    val userId: Int?
)