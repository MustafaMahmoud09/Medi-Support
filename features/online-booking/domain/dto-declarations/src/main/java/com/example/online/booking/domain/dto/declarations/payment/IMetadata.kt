package com.example.online.booking.data.source.remote.data.dto.execution.payment


import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("booking_id")
    val bookingId: String?,
    @SerializedName("doctor_id")
    val doctorId: String?,
    @SerializedName("user_id")
    val userId: String?
)