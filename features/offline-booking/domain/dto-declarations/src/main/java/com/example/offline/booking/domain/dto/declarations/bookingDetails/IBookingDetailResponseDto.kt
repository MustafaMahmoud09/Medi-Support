package com.example.offline.booking.data.source.remote.data.dto.execution.bookingDetails


import com.google.gson.annotations.SerializedName

data class BookingDetailResponseDto(
    @SerializedName("data")
    val `data`: BookingDetailData?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)