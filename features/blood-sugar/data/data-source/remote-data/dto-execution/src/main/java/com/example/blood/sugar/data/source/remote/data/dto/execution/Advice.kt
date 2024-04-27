package com.example.blood.sugar.data.source.remote.data.dto.execution.lastRecords


import com.google.gson.annotations.SerializedName

data class Advice(
    @SerializedName("advice")
    val advice: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("key")
    val key: String?
)