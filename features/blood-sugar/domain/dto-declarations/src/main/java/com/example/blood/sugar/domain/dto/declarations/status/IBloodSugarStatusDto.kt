package com.example.blood.sugar.data.source.remote.data.dto.execution.status


import com.google.gson.annotations.SerializedName

data class BloodSugarStatusDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("status-name")
    val statusName: String?
)