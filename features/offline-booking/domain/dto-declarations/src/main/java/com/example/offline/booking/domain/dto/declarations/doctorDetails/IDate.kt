package com.example.offline.booking.data.source.remote.data.dto.execution.doctorDetails


import com.google.gson.annotations.SerializedName

data class Date(
    @SerializedName("date")
    val date: String?,
    @SerializedName("id")
    val id: Int?
)