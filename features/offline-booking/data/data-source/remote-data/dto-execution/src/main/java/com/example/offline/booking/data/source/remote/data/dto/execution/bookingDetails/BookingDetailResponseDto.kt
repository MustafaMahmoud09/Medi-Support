package com.example.offline.booking.data.source.remote.data.dto.execution.bookingDetails


import com.google.gson.annotations.SerializedName

data class OBookingDetailResponseDto(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)