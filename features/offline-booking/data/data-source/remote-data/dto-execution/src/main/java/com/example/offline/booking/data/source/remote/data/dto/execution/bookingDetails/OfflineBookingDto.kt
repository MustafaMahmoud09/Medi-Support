package com.example.offline.booking.data.source.remote.data.dto.execution.bookingDetails


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("clinic_location")
    val clinicLocation: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("specialization")
    val specialization: String?,
    @SerializedName("time")
    val time: String?
)