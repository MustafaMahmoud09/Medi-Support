package com.example.bmi.data.source.remote.data.dto.execution.lastRecords


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("advice")
    val advice: String?,
    @SerializedName("advice_id")
    val adviceId: Int?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("day-name")
    val dayName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("key")
    val key: String?,
    @SerializedName("result")
    val result: Double?,
    @SerializedName("user_id")
    val userId: Int?
)