package com.example.blood.pressure.data.source.remote.data.dto.execution.pageMeasurement


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("first")
    val first: String?,
    @SerializedName("last")
    val last: String?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: Any?
)