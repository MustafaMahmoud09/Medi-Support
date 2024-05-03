package com.example.offline.booking.data.source.remote.data.dto.execution.doctorTime


import com.google.gson.annotations.SerializedName

data class Time(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("time")
    val time: String?
)