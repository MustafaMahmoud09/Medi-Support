package com.example.online.booking.data.source.remote.data.dto.execution.bookingDetails


import com.google.gson.annotations.SerializedName

data class BookingDetailDto(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("doctor_name")
    val doctorName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("specialization")
    val specialization: String?,
    @SerializedName("active_status")
    val activeStatus: Int?,
)