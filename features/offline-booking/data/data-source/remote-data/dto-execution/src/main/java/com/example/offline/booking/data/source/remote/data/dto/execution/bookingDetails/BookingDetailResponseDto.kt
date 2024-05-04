package com.example.offline.booking.data.source.remote.data.dto.execution.bookingDetails

import com.example.offline.booking.domain.dto.declarations.bookingDetails.IBookingDetailResponseDto
import com.google.gson.annotations.SerializedName

data class BookingDetailResponseDto(
    @SerializedName("data")
    override val `data`: BookingDetailData?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
): IBookingDetailResponseDto